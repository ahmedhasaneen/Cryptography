package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.crypto.CaeserCipher;

@Service
public class CaeserService {
	
	public String encrypt(String text, String key) {
		 String result = CaeserCipher.encrypt(text, Integer.parseInt(key));
		return result;
	}
	public String decrypt(String text, String key) {
		String result = CaeserCipher.decrypt(text, Integer.parseInt(key));
		return result;
	}
}




