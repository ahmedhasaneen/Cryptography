package com.example.demo.crypto;

import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {

	   private static final String ALGORITHM = "AES";
	    private static final int KEY_SIZE = 16; // 16 bytes for AES-128

	    public static String encrypt(String plainText, String key) throws Exception {
	        SecretKeySpec secretKey = KeyUtil.createSecretKey(key, ALGORITHM, KEY_SIZE);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }

	    public static String decrypt(String cipherText, String key) throws Exception {
	        SecretKeySpec secretKey = KeyUtil.createSecretKey(key, ALGORITHM, KEY_SIZE);
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
	        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	        return new String(decryptedBytes);
	    }
}
