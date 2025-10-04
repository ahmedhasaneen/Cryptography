package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.model.Text;
import com.example.demo.service.CaeserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("api/caeser")
public class CaeserController {
	
	private CaeserService caeserService;
	
	public CaeserController(CaeserService caeserService) {
		this.caeserService = caeserService;
	}

	@GetMapping
	public String mainPage( Model model) {
		model.addAttribute("text", new Text());
		return "main";
	}
	
	@PostMapping("/")
	public String CaeserCipher(@ModelAttribute("text") Text text ,@ModelAttribute("action") String action ,  Model model) {
		try {
			if ("encode".equals(action)) {
				text.setCipherText( caeserService.encrypt(text.getPlainText(), text.getKey()));			
			} else if("decode".equals(action)) {
				text.setCipherText( caeserService.decrypt(text.getPlainText(), text.getKey()));
			}
			 model.addAttribute("text", text);
		} catch (Exception error) {
			model.addAttribute("error", "In valied shift key");
			error.printStackTrace();
		}
		
		return "main";
		
	}
	
}
