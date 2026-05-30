package com.doruk.infrastructure.messaging.handler;

import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.logging.LoggingService;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.json.JsonMapper;
import io.micronaut.runtime.event.annotation.EventListener;
import io.nats.client.Connection;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.PullSubscribeOptions;
import io.nats.client.JetStreamApiException;
import io.nats.client.JetStreamManagement;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerInfo;
import io.nats.client.api.RetentionPolicy;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import jakarta.annotation.PreDestroy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class EventConsumer<T> {
    protected enum AckAction {
        ACK, NAK, TERM
    }

    protected final AppExecutors executors;
    protected final Connection natsConnection;
    protected final JsonMapper jsonMapper;

    protected volatile JetStreamSubscription subscription;
    protected volatile boolean running = true;
    protected volatile Future<?> loopFuture;

    public EventConsumer(AppExecutors executors, Connection natsConnection, JsonMapper jsonMapper) {
        this.executors = executors;
        this.natsConnection = natsConnection;
        this.jsonMapper = jsonMapper;
    }

    protected String stream() {
        return "microsample-events";
    }

    protected abstract String subject();

    protected abstract String durable();

    protected abstract String fetchErrorMsg();

    protected abstract String deserializationErrorMsg();

    protected abstract String processFailureMsg();

    protected abstract Class<T> getConsumedEventTypeClass();

    protected abstract AckAction processEvent(T event);

    @EventListener
    void start(StartupEvent event) throws Exception {
        ensureStreamExists();
        ensureConsumer();

        var options = PullSubscribeOptions.builder()
                .stream(this.stream())
                .configuration(ConsumerConfiguration.builder()
                        .durable(this.durable())
                        .filterSubject(this.subject())
                        .ackWait(Duration.ofMinutes(5))
                        .maxDeliver(5)
                        .backoff(Duration.ofSeconds(1), Duration.ofSeconds(5),
                                Duration.ofSeconds(30), Duration.ofSeconds(60))
                        .build())
                .build();

        this.subscription = natsConnection.jetStream().subscribe(this.subject(), options);
        this.loopFuture = executors.VIRTUAL().submit(this::loop);
    }

    private void ensureStreamExists() {
        try {
            JetStreamManagement jsm = natsConnection.jetStreamManagement();
            try {
                jsm.getStreamInfo(stream());
            } catch (JetStreamApiException e) {
                if (e.getErrorCode() == 10059) {
                    StreamConfiguration config = StreamConfiguration.builder()
                            .name(stream())
                            .subjects("event.>", "file.>", "profile.>")
                            .storageType(StorageType.File)
                            .retentionPolicy(RetentionPolicy.Interest)
                            .maxAge(Duration.ofDays(7))
                            .build();
                    jsm.addStream(config);
                    LoggingService.logInfo("Created JetStream stream: " + stream());
                } else {
                    throw e;
                }
            }
        } catch (IOException | JetStreamApiException e) {
            LoggingService.logError("Failed to ensure JetStream stream '" + stream() + "': " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void ensureConsumer() {
        try {
            JetStreamManagement jsm = natsConnection.jetStreamManagement();
            ConsumerInfo info = jsm.getConsumerInfo(stream(), durable());
            ConsumerConfiguration existing = info.getConsumerConfiguration();
            Duration currentAckWait = Duration.ofMinutes(5);
            boolean configChanged = !currentAckWait.equals(existing.getAckWait());

            if (!configChanged) {
                List<Duration> currentBackoff = List.of(
                        Duration.ofSeconds(1), Duration.ofSeconds(5),
                        Duration.ofSeconds(30), Duration.ofSeconds(60)
                );
                configChanged = !currentBackoff.equals(existing.getBackoff());
            }

            if (configChanged) {
                jsm.deleteConsumer(stream(), durable());
                LoggingService.logInfo("Deleted consumer '" + durable() + "' due to configuration change");
            }
        } catch (JetStreamApiException e) {
            // Consumer doesn't exist — will be created fresh by subscribe
        } catch (IOException e) {
            LoggingService.logError("Failed to check consumer '" + durable() + "': " + e.getMessage());
        }
    }

    private void loop() {
        while (running) {
            try {
                List<Message> messages = subscription.fetch(10, Duration.ofSeconds(1));
                for (Message msg : messages) {
                    try {
                        switch(
                                this.deserializeEvent(msg, this.getConsumedEventTypeClass())
                                .map(this::processEvent)
                                .orElse(AckAction.TERM)
                        ) {
                            case ACK -> msg.ack();
                            case NAK -> msg.nak();
                            case TERM -> msg.term();
                        }
                    } catch (Exception e) {
                        LoggingService.logError(this.processFailureMsg(), e);
                        msg.nak();
                    }
                }
            } catch (Exception e) {
                if (running)
                    LoggingService.logError(this.fetchErrorMsg(), e);
            }
        }
    }

    protected Optional<T> deserializeEvent(Message msg, Class<T> clazz) {
        T event = null;
        try {
            event = jsonMapper.readValue(msg.getData(), clazz);
        } catch (IOException e) {
            LoggingService.logError(this.deserializationErrorMsg(), e);
        }
        return Optional.ofNullable(event);
    }

    @PreDestroy
    protected void shutdown() {
        running = false;

        if (loopFuture != null) {
            try {
                loopFuture.get(5, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                loopFuture.cancel(true);
            }
            catch (Exception ignored) {
            }
        }

        if (subscription != null) {
            try {
                subscription.unsubscribe();
            } catch (Exception ignored) {
            }
        }
    }
}