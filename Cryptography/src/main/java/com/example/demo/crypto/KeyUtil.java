package com.example.demo.crypto;

import javax.crypto.spec.SecretKeySpec;

public class KeyUtil {

    public static SecretKeySpec createSecretKey(String key, String algorithm, int size) {
        byte[] keyBytes = resizeKey(key, size);
        return new SecretKeySpec(keyBytes, algorithm);
    }

    private static byte[] resizeKey(String key, int size) {
        byte[] keyBytes = new byte[size];
        byte[] originalBytes = key.getBytes();
        for (int i = 0; i < keyBytes.length; i++) {
            keyBytes[i] = (i < originalBytes.length) ? originalBytes[i] : 0;
        }
        return keyBytes;
    }
}
