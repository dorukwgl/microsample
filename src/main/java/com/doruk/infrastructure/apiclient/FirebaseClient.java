package com.doruk.infrastructure.apiclient;

import com.doruk.infrastructure.config.FirebaseConfig;
import com.doruk.infrastructure.logging.LoggingService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Context;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.HttpVersion;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.DefaultHttpClientConfiguration;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.concurrent.locks.ReentrantLock;

@Singleton
@Context
public class FirebaseClient {
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String FCM_BASE = "https://fcm.googleapis.com/v1/projects/";

    private final String projectId;
    private final String clientEmail;
    private final java.security.PrivateKey privateKey;
    private final HttpClient httpClient;
    private final HttpClient oauthHttpClient;

    private volatile String accessToken;
    private volatile Instant tokenExpiry = Instant.EPOCH;
    private final ReentrantLock tokenLock = new ReentrantLock();

    public FirebaseClient(FirebaseConfig config) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(config.serviceAccountJson());
            this.projectId = json.get("project_id").asText();
            this.clientEmail = json.get("client_email").asText();
            String rawKey = json.get("private_key").asText()
                    .replace("\\n", "\n")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");
            byte[] keyBytes = Base64.getDecoder().decode(rawKey);
            this.privateKey = KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Firebase service account JSON", e);
        }
        try {
            var fcmConfig = new DefaultHttpClientConfiguration();
            fcmConfig.setHttpVersion(HttpVersion.HTTP_2_0);
            fcmConfig.getConnectionPoolConfiguration().setMaxConcurrentRequestsPerHttp2Connection(600);
            this.httpClient = HttpClient.create(URI.create(FCM_BASE).toURL(), fcmConfig);
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Invalid FCM base URL", e);
        }
        try {
            this.oauthHttpClient = HttpClient.create(URI.create(TOKEN_URL).toURL());
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Invalid OAuth token URL", e);
        }
    }

    public void send(String token, String title, String body, String icon, String image) {
        var request = buildRequest(token, title, body, icon, image);
        String payload = serialize(request);

        HttpResponse<String> response = httpClient.toBlocking()
                .exchange(
                        HttpRequest.POST(FCM_BASE + projectId + "/messages:send", payload)
                                .contentType(MediaType.APPLICATION_JSON_TYPE)
                                .bearerAuth(getAccessToken()),
                        String.class
                );

        if (response.getStatus() != HttpStatus.OK) {
            LoggingService.logError("FCM send failed for token :"+ token + ": " + response.getStatus() + " : " + response.body());
        }
    }

    private String getAccessToken() {
        if (Instant.now().isBefore(tokenExpiry.minusSeconds(60))) return accessToken;

        tokenLock.lock();
        try {
            if (Instant.now().isBefore(tokenExpiry.minusSeconds(60))) return accessToken;
            refreshToken();
            return accessToken;
        } finally {
            tokenLock.unlock();
        }
    }

    private void refreshToken() {
        try {
            String jwt = createJwt();
            String body = "grant_type=urn%3Aietf%3Aparams%3Aoauth%3Agrant-type%3Ajwt-bearer&assertion=" + jwt;

            HttpResponse<String> response = oauthHttpClient.toBlocking()
                    .exchange(
                            HttpRequest.POST(TOKEN_URL, body)
                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE),
                            String.class
                    );

            JsonNode json = new ObjectMapper().readTree(response.body());
            this.accessToken = json.get("access_token").asText();
            this.tokenExpiry = Instant.now().plusSeconds(json.get("expires_in").asLong());
        } catch (Exception e) {
            throw new RuntimeException("Failed to obtain Firebase access token", e);
        }
    }

    private String createJwt() throws Exception {
        long now = Instant.now().getEpochSecond();
        String header = Base64.getUrlEncoder().withoutPadding()
                .encodeToString("{\"alg\":\"RS256\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        String claimSet = Base64.getUrlEncoder().withoutPadding()
                .encodeToString(("{\"iss\":\"" + clientEmail + "\"," +
                        "\"scope\":\"" + SCOPE + "\"," +
                        "\"aud\":\"" + TOKEN_URL + "\"," +
                        "\"exp\":" + (now + 3600) + "," +
                        "\"iat\":" + now + "}")
                        .getBytes(StandardCharsets.UTF_8));

        String signingInput = header + "." + claimSet;
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(privateKey);
        sig.update(signingInput.getBytes(StandardCharsets.UTF_8));
        String signature = Base64.getUrlEncoder().withoutPadding().encodeToString(sig.sign());

        return signingInput + "." + signature;
    }

    private Object buildRequest(String token, String title, String body, String icon, String image) {
        var notification = new java.util.LinkedHashMap<String, Object>();
        notification.put("title", title);
        notification.put("body", body);
        if (image != null) notification.put("image", image);

        var message = new java.util.LinkedHashMap<String, Object>();
        message.put("token", token);
        message.put("notification", notification);

        if (icon != null || image != null) {
            var androidConfig = new java.util.LinkedHashMap<String, Object>();
            var androidNotif = new java.util.LinkedHashMap<String, Object>();
            if (icon != null) androidNotif.put("icon", icon);
            if (image != null) androidNotif.put("image", image);
            androidConfig.put("notification", androidNotif);
            message.put("android", androidConfig);
        }

        if (image != null) {
            var apnsConfig = new java.util.LinkedHashMap<String, Object>();
            var aps = new java.util.LinkedHashMap<String, Object>();
            aps.put("mutable-content", 1);
            apnsConfig.put("payload", java.util.Map.of("aps", aps));
            apnsConfig.put("fcm_options", java.util.Map.of("image", image));
            message.put("apns", apnsConfig);
        }

        return java.util.Map.of("message", message);
    }

    private String serialize(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize FCM request", e);
        }
    }
}
