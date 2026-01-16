package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.app.auth.dto.UploadedFileResult;
import com.doruk.application.events.ProfileImageUpload;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.util.Constants;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Singleton;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Singleton
@NatsListener
@RequiredArgsConstructor
public class ImageUploadEventHandler {
    private final AppExecutors executors;
    private final AppConfig config;

    private void scaleAndCompress(File file, int size, File dest) {
        var t = Thumbnails.of(file)
                .outputQuality(0.7f);

        if (size != Constants.FULL_SIZE)
            t.size(size, size);

        try {
            t.toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File createDestFile(String storedName, String scale) {
        return Path.of(config.publicUploadPath(), scale, storedName).toFile();
    }

    private void storeFile(UploadedFileResult file) {
        try {
            List.of(
                    new Pair<>(Constants.PICO_SIZE, Constants.PICO_DIR),
                    new Pair<>(Constants.SMALL_SIZE, Constants.SMALL_DIR),
                    new Pair<>(Constants.MEDIUM_SIZE, Constants.MEDIUM_DIR),
                    new Pair<>(Constants.FULL_SIZE, Constants.FULL_DIR)
            ).forEach(p -> scaleAndCompress(
                    file.fullPath().toFile(),
                    p.getKey(),
                    createDestFile(file.storedName(), p.getValue())
            ));
            Files.delete(file.fullPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String storedName) {

        List.of(
                Constants.PICO_DIR,
                Constants.SMALL_DIR,
                Constants.MEDIUM_DIR,
                Constants.FULL_DIR
        ).forEach(dir -> {
            try {
                Files.deleteIfExists(Path.of(config.publicUploadPath(), dir, storedName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Subject(value = "image.profile.upload", queue = "image-upload-queue")
    public void handle(ProfileImageUpload event) {
        CompletableFuture.runAsync(() -> storeFile(event.file()), executors.CPU())
                .thenRunAsync(() -> deleteFile(event.file().storedName()), executors.VIRTUAL());
    }

//    @Subject(value = "file.image.upload.multi", queue = "image-upload-multi-queue")
//    public void handle(MultiImageUpload event) {
//        // parallel processing
//        event.files().forEach(file -> CompletableFuture.runAsync(() ->
//                this.storeFile(file), executors.CPU()));
//    }
}
