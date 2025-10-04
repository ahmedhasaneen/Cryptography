package com.example.demo.controller;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Text;
import com.example.demo.service.ModrenService;

@Controller
@RequestMapping("api/ModrenCipher")
@SessionAttributes({"publickey", "privatekey"})
public class ModrenController {

	private ModrenService modrenService;
	
	public ModrenController(ModrenService modrenService) {
		this.modrenService = modrenService;
	}
	
	@GetMapping("DES")
	public String NewDES( Model model) {
		model.addAttribute("text" ,new Text());
		return "DES";
	}
	
	@PostMapping("DES/")
	public String DES_Crypto(@ModelAttribute("text") Text text, @ModelAttribute("action") String action  , Model model) throws Exception {
		try {
			if ("encode".equals(action)) {
				text.setCipherText( modrenService.encrypt("DES", text.getPlainText(),text.getKey()));			
			} else if("decode".equals(action)) {
				text.setCipherText( modrenService.decrypt("DES", text.getPlainText(), text.getKey()));
			}
			 model.addAttribute("text", text);
		} catch (Exception error) {
			model.addAttribute("error", "In valied  key");
			error.printStackTrace();
		}
		 
		 return "DES";
	}
	
	
	@GetMapping("AES")
	public String NewAES( Model model) {
		model.addAttribute("text" ,new Text());
		return "AES";
	}
	
	@PostMapping("AES/")
	public String AES_Crypto(@ModelAttribute("text") Text text, @ModelAttribute("action") String action  , Model model) throws Exception {
		try {
			if ("encode".equals(action)) {
				text.setCipherText( modrenService.encrypt("AES", text.getPlainText(),text.getKey()));			
			} else if("decode".equals(action)) {
				text.setCipherText( modrenService.decrypt("AES", text.getPlainText(), text.getKey()));
			}
			model.addAttribute("text", text);
		} catch (Exception error) {
			model.addAttribute("error", "In valied  key");
			error.printStackTrace();
		}
		
		return "AES";
	}
	
	@GetMapping("RSA")
	public String NewRSA( Model model) {
		model.addAttribute("text" ,new Text());
		return "RSA";
	}
	
	
	
	@PostMapping("RSA/")
	public String RSA_Encrypt(@ModelAttribute("text") Text text, @ModelAttribute("action") String action, @ModelAttribute("keys") String keys , Model model) throws Exception {
		try {
			
			//this is for to generate the keys
			if ("generate".equals(keys)) {
		        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		        generator.initialize(2048); // 2048-bit keys
		        KeyPair keyPair = generator.generateKeyPair();

		        PublicKey publicKey = keyPair.getPublic();
		        PrivateKey privateKey = keyPair.getPrivate();

		        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		        
		        text.setPublickey(publicKeyBase64);
		        text.setPrivatekey(privateKeyBase64);
		        
		        model.addAttribute("publickey", publicKeyBase64);
	            model.addAttribute("privatekey", privateKeyBase64);
		        
			}
			
			
	        // Restore keys from session if needed
	        if ((text.getPublickey() == null || text.getPublickey().isEmpty()) &&
	            model.getAttribute("publickey") != null) {
	            text.setPublickey((String) model.getAttribute("publickey"));
	        }

	        if ((text.getPrivatekey() == null || text.getPrivatekey().isEmpty()) &&
	            model.getAttribute("privatekey") != null) {
	            text.setPrivatekey((String) model.getAttribute("privatekey"));
	        }
			
			if ("encode".equals(action)) {
				text.setCipherText( modrenService.encrypt("RSA", text.getPlainText(),text.getKey()));			
			} else if("decode".equals(action)) {
				text.setCipherText( modrenService.decrypt("RSA", text.getPlainText(), text.getKey()));
			}
			model.addAttribute("text", text);
		} catch (Exception error) {
			model.addAttribute("error", "In valied  key");
			error.printStackTrace();
		}
		
		return "RSA";
	}
	
	
}
