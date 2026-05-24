package com.doruk.infrastructure.apiclient;

import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.logging.LoggingService;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Singleton
@RequiredArgsConstructor
public class FirebasePushService {

    private final FirebaseClient firebaseClient;
    private final AppExecutors executor;

    public void sendBulk(List<String> deviceTokens, String title, String body, String icon, String image) {
        if (deviceTokens == null || deviceTokens.isEmpty()) return;

        var futures = new ArrayList<CompletableFuture<Void>>(deviceTokens.size());
        for (var token : deviceTokens) {
            futures.add(
                    CompletableFuture.runAsync(
                            () -> firebaseClient.send(token, title, body, icon, image),
                            executor.VIRTUAL()
                    ).whenComplete((_, ex) -> {
                        if (ex != null) {
                            LoggingService.logError("FCM push failed for token " + token + ": " + ex.getMessage());
                        }
                    }).exceptionally(_ -> null)
            );
        }
        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } catch (Exception e) {
            LoggingService.logError("FCM batch send interrupted: " + e.getMessage());
        }
    }
}
