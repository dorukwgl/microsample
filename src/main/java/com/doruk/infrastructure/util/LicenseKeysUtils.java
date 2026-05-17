package com.doruk.infrastructure.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class LicenseKeysUtils {
    private static SecureRandom random;

    public static String createLicenseKey() {
        try {
            if (random == null)
                random = SecureRandom.getInstanceStrong();

            var bytes = new byte[24];
            random.nextBytes(bytes);

            var encoded = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
            return encoded.replaceAll("(.{8})", "$1-")
                    .replaceAll("-$", "");

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
