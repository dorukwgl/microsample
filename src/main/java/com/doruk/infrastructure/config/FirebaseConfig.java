package com.doruk.infrastructure.config;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("firebase")
public record FirebaseConfig(
        // Service account JSON file content or inline JSON.
        // Generate from: Firebase Console → Project Settings → Service Accounts → Generate New Private Key
        // Env: FIREBASE_SERVICE_ACCOUNT_JSON
        String serviceAccountJson
) {}
