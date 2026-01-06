package com.doruk.infrastructure.util;

public class KeyNamespace {
    public static String mfaTransactionId(String token) {
        return "mfa:txn:" + token;
    }

    public static String mfaOtpAttempt(String token) {
        return "mfa:txn:" + token + ":attempt";
    }

    public static String verificationTransactionId(String token) {
        return "vrf:txn:" + token;
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
}
