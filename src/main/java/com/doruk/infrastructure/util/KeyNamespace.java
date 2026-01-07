package com.doruk.infrastructure.util;

public class KeyNamespace {
    public static String mfaTransactionId(String token) {
        return "mfa:txn:" + token;
    }

    public static String mfaOtpAttempt(String token) {
        return "mfa:txn:" + token + ":attempt";
    }

    public static String mfaOtpCooldown(String token) {
        return "mfa:txn:" + token + ":cooldown";
    }

    public static String verificationTransactionId(String token) {
        return "vrf:txn:" + token;
    }

    public static String verificationOtpCooldown(String token) {
        return "vrf:txn:" + token + ":cooldown";
    }

    public static String verificationOtpAttempt(String token) {
        return "vrf:txn:" + token + ":attempt";
    }

    public static String verificationMagicId(String token) {
        return "vrf:magic:txn:" + token;
    }

    public static String updateAuthTransactionId(String token) {
        return "auth:update:txn:" + token;
    }

    public static String updateAuthOtpAttempt(String token) {
        return "auth:update:txn:" + token + ":attempt";
    }

    public static String updateAuthOtpCooldown(String token) {
        return "auth:update:txn:" + token + ":cooldown";
    }

    public static String resetPasswordTransactionId(String token) {
        return "reset:pw:txn" + token;
    }

    public static String resetPasswordOtpAttempts(String token) {
        return "reset:pw:txn" + token + ":attempt";
    }

    public static String resetPasswordOtpCooldown(String token) {
        return "reset:pw:txn" + token + ":cooldown";
    }

    public static String resetPasswordMagicLink(String token) {
        return "reset:pw:txn:magic" + token;
    }
}
