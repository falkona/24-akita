package ru.netology.utils;

import lombok.Data;
import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static Card getCard1Info() {
        return new Card("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559000000000001", 10000.00, "0001");
    }

    public static Card getCard2Info() {
        return new Card("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559000000000002", 10000.00, "0002");
    }
}
