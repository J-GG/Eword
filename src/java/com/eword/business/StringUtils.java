package com.eword.business;

import java.security.MessageDigest;
import java.util.Random;

public class StringUtils {

    /**
     * Return the encryption of the text with SHA-256
     *
     * @param text The text to encrypt
     * @return The encrypted text
     */
    public static String sha256(String text) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Generate a random string containing special characters
     *
     * @param length The length of the string
     * @return A random String
     */
    public static String randomString(int length) {

        StringBuilder string = new StringBuilder();
        Random random = new Random();

        //Characters which can be used to generate the string
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?".toCharArray();

        for (int i = 0; i <= length; i++) {
            char c = chars[random.nextInt(chars.length)];
            string.append(c);
        }

        return string.toString();
    }
}
