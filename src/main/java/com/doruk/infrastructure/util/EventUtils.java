package com.doruk.infrastructure.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public class EventUtils {
    private static MessageDigest digest;

    public static String generateCallId(String eventData) {
        try {
            if (digest == null)
                digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(eventData.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
