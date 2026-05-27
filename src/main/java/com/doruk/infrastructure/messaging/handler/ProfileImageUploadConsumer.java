package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.enums.ImageVariant;
import com.doruk.application.events.ProfileImageUploadEvent;
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
public class ProfileImageUploadConsumer extends EventConsumer<ProfileImageUploadEvent> {
    private final ObjectStorage storage;

    public ProfileImageUploadConsumer(AppExecutors executors,
                                      Connection natsConnection,
                                      JsonMapper jsonMapper,
                                      ObjectStorage storage) {
        super(executors, natsConnection, jsonMapper);
        this.storage = storage;
    }

    @Override
    protected String subject() {
        return "profile.image.upload.event";
    }

    @Override
    protected String durable() {
        return "profile-image-upload-consumer";
    }

    @Override
    protected String fetchErrorMsg() {
        return "Fetch Error: failed to fetch message durable: profile-image-upload-consumer";
    }

    @Override
    protected String deserializationErrorMsg() {
        return "Deserialization Error: Failed to deserialize profile image upload event, terminating";
    }

    @Override
    protected String processFailureMsg() {
        return "Profile image upload event processing failed";
    }

    @Override
    protected Class<ProfileImageUploadEvent> getConsumedEventTypeClass() {
        return ProfileImageUploadEvent.class;
    }

    @Override
    protected AckAction processEvent(ProfileImageUploadEvent event) {
        handleScaling(event);
        deleteOldVariantFiles(event);

        return AckAction.ACK;
    }

    private void deleteOldVariantFiles(ProfileImageUploadEvent event) {
        if (event.oldObjectKey().isEmpty())
            return;

        var objectKey = event.oldObjectKey().get();
        for (var variant : ImageVariant.values()) {
            String variantKey = ImageVariantKey.of(objectKey, variant);
            storage.delete(variantKey);
        }
    }

    private void handleScaling(ProfileImageUploadEvent event) {
        for (ImageVariant variant : ImageVariant.values()) {
            try (InputStream imgVariants = storage.open(event.objectKey())) {
                var data = CompletableFuture
                        .supplyAsync(() -> ImageScalingUtil.scaleAndCompress(imgVariants, variant), executors.CPU())
                        .join();

                String variantKey = ImageVariantKey.of(event.objectKey(), variant);
                storage.put(variantKey, data.getValue(), data.getKey(), event.mimeType());
            } catch (IOException e) {
                throw new RuntimeException("Variant generation failed", e);
            }
        }
    }
}
