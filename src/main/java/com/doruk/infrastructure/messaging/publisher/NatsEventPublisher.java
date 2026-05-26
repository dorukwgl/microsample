package com.doruk.infrastructure.messaging.publisher;

import com.doruk.application.interfaces.EventDto;
import com.doruk.application.interfaces.EventPublisher;
import io.micronaut.context.annotation.Bean;
import io.micronaut.json.JsonMapper;
import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Singleton
@Bean(typed = EventPublisher.class)
@RequiredArgsConstructor
public class NatsEventPublisher implements EventPublisher {
    private final Connection natsConnection;
    private final JsonMapper jsonMapper;
    private volatile JetStream jetStream;

    private JetStream jetStream() throws IOException {
        if (jetStream == null) {
            jetStream = natsConnection.jetStream();
        }
        return jetStream;
    }

    @Override
    public void publish(EventDto eventDto) {
        try {
            jetStream().publish(eventDto.eventSubject(),
                    jsonMapper.writeValueAsBytes(eventDto));
        } catch (IOException | JetStreamApiException e) {
            throw new RuntimeException(e);
        }
    }

}
