package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.enums.ImageVariant;
import com.doruk.application.events.MultiImageUploadEvent;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.util.ImageScalingUtil;
import com.doruk.infrastructure.util.ImageVariantKey;
import io.micronaut.context.annotation.Context;
import io.micronaut.json.JsonMapper;
import io.nats.client.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

@Context
public class MultiImageUploadConsumer extends EventConsumer<MultiImageUploadEvent> {
    private final ObjectStorage storage;

    public MultiImageUploadConsumer(AppExecutors executors,
                                    Connection natsConnection,
                                    JsonMapper jsonMapper,
                                    ObjectStorage storage) {
        super(executors, natsConnection, jsonMapper);
        this.storage = storage;
    }

    @Override
    protected String subject() {
        return "file.image.upload.multi";
    }

    @Override
    protected String durable() {
        return "multi-image-upload-consumer";
    }

    @Override
    protected String fetchErrorMsg() {
        return "Fetch Error: Failed to fetch message durable: multi-image-upload-consumer";
    }

    @Override
    protected String deserializationErrorMsg() {
        return "Deserialization Error: failed to deserialize multi image upload event, terminating";
    }

    @Override
    protected String processFailureMsg() {
        return "Multi image upload processing failed";
    }

    @Override
    protected Class<MultiImageUploadEvent> getConsumedEventTypeClass() {
        return MultiImageUploadEvent.class;
    }

    @Override
    protected AckAction processEvent(MultiImageUploadEvent event) {
        var tasks = event.files().stream()
                .map(f -> CompletableFuture.runAsync(() -> handleScalingFor(f), executors.CPU()))
                .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
        return AckAction.ACK;
    }

    private void handleScalingFor(MultiImageUploadEvent.FilePayload file) {
        for (ImageVariant variant : ImageVariant.values()) {
            try (InputStream stream = storage.open(file.objectKey())) {
                var data = ImageScalingUtil.scaleAndCompress(stream, variant);
                String variantKey = ImageVariantKey.of(file.objectKey(), variant);
                storage.put(variantKey, data.value(), data.key(), file.mimeType());
            } catch (IOException e) {
                throw new RuntimeException("Variant generation failed", e);
            }
        }
    }
}
