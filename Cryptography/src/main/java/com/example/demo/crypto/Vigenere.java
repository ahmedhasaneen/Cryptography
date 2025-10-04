package com.example.demo.crypto;


public class Vigenere {

    private static String formatKey(String key, int length) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        return key.repeat((length / key.length()) + 1).substring(0, length);
    }

    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        key = formatKey(key, plaintext.length());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char c = (char) ((plaintext.charAt(i) + key.charAt(i) - 2 * 'A') % 26 + 'A');
            result.append(c);
        }
        return result.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
        key = formatKey(key, ciphertext.length());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = (char) ((ciphertext.charAt(i) - key.charAt(i) + 26) % 26 + 'A');
            result.append(c);
        }
        return result.toString();
    }
}

