package com.raghavx.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RechargeController {

	@GetMapping("/recharge")
	public String recharge(){
		return "recharge";
	}
	
	@PostMapping("/recharge")
	public String recharge(Model model){
		return "recharge";
	}
	
	@PostMapping("/quickrecharge")
	public String quickrecharge(Model model){
		return "redirect:/recharge";
	}
}
