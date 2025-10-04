package com.example.demo.model;

public class Text {

	private String plainText;
	private String key;
	private String publickey;
	private String privatekey;
	private String cipherText;
	
	public String getPublickey() {
		return publickey;
	}
	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}
	public String getPrivatekey() {
		return privatekey;
	}
	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}
	public String getCipherText() {
		return cipherText;
	}
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}
	public String getPlainText() {
		return plainText;
	}
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	
	public Text(String plainText, String key, String publickey, String privatekey, String cipherText) {
		super();
		this.plainText = plainText;
		this.key = key;
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.cipherText = cipherText;
	}
	public Text() {
		super();
	}

}
