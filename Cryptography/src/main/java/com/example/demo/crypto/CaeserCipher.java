package com.example.demo.crypto;

public class CaeserCipher {
	
	
	
	public CaeserCipher() {
		super();
	}
	
	public static String encrypt(String text, int shift) {
		//StringBuilder is fast than String 
		StringBuilder result = new StringBuilder();
		
		//this to pass on every char in the string
		for(char c : text.toCharArray()) {
			
			if(Character.isLetter(c)) {
				char base = Character.isUpperCase(c) ? 'A' : 'a';
				
				result.append((char) ((c - base + shift) % 26 + base));
			} else {
				result.append(c);
			}
			
		}
		
		return result.toString();
	}
	
	
	public static String decrypt(String text, int shift) {
		return encrypt(text, 26 - shift);
	}
	
}
