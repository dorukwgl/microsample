package com.doruk.infrastructure.util;

public final class Constants {
    public final static int MFA_VALIDITY_SECONDS = 120;
    public final static int MFA_ATTEMPT_LIMIT = 2;
    public final static int OTP_VALIDITY_SECONDS = 120;
    public final static int MAGIC_LINK_VALIDITY_SECONDS = 900;
    public final static String SESSION_COOKIE_HEADER = "X-State-Transfer";
    public final static String PICO_DIR = "pico";
    public final static String SMALL_DIR = "small";
    public final static String MEDIUM_DIR = "medium";
    public final static String FULL_DIR = "full";
    public final static int PICO_SIZE = 64;
    public final static int SMALL_SIZE = 320;
    public final static int MEDIUM_SIZE = 640;
    public final static int FULL_SIZE = -1; // full size
    public final static int AUTH_UPDATE_VALIDITY_SECONDS = 180;
    public final static int AUTH_UPDATE_ATTEMPT_LIMIT = 2;
    public final static int PW_UPDATE_OTP_ATTEMPT_LIMIT = 2;
    public final static int RESEND_OTP_COOLDOWN_SECONDS = 30;
    public final static int PW_RESET_VALIDITY_SECONDS = 300;
}
