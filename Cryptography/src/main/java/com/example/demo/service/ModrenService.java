package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.crypto.AES;
import com.example.demo.crypto.DES;
import com.example.demo.crypto.RSA;
@Service
public class ModrenService {

    public  String encrypt(String algorithm, String plainText, String key) throws Exception {
        switch (algorithm.toUpperCase()) {
            case "AES":
                return AES.encrypt(plainText, key);
            case "DES":
                return DES.encrypt(plainText, key);
            case "RSA":
                return RSA.encrypt(plainText, key); // key = public key
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }

    public  String decrypt(String algorithm, String cipherText, String key) throws Exception {
        switch (algorithm.toUpperCase()) {
            case "AES":
                return AES.decrypt(cipherText, key);
            case "DES":
                return DES.decrypt(cipherText, key);
            case "RSA":
                return RSA.decrypt(cipherText, key); // key = private key
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }
    
}

