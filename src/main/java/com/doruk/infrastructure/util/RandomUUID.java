package com.doruk.infrastructure.util;

import java.util.UUID;

public class RandomUUID {
    public static String generateSessionId() {
        return UUID.randomUUID().toString() + UUID.randomUUID() + UUID.randomUUID();
    }

    public static String generateMfaToken() {
        return UUID.randomUUID().toString() + UUID.randomUUID();
    }
}
