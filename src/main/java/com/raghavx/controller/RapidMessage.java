package com.raghavx.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RapidMessage {

	@GetMapping("/upload/bulk")
	public String uploadBulk(){
		return null;
	}
	
	@PostMapping("/upload/bulk")
	public Callable<String> uploadBulk(Model model){
		return null;
	}
	
	@GetMapping("/compose/message")
	public String composeMessage(){
		return null;
	}
}
