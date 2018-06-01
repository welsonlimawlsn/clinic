package br.com.welson.clinic.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class CryptographyUtil {

    public static String encodePassword(String username, String password) {
        return encode(username + "_" + password);
    }

    public static String encodeEmailForRecoveyAndConfirmation(String email, LocalDateTime expirationTime) {
        return encode(email + "_" + expirationTime.toString());
    }

    private static String encode(String s) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte[] digest = algorithm.digest(s.getBytes(Charset.forName("UTF-8")));
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
