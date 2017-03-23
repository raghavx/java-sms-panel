package com.raghavx.controller;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class SmartReportsController {

	@GetMapping("/sent/sms")
	public Callable<String> sentSms(){
		return null;
	}
	
	@GetMapping("/scheduled/sms")
	public Callable<String> scheduledSms(){
		return null;
	}
	
	@GetMapping("/mis")
	public Callable<String> mis(){
		return null;
	}
	
	@GetMapping("/dlr")
	public Callable<String> dlr(){
		return null;
	}
	
	@GetMapping("/route")
	public Callable<String> route(){
		return null;
	}
}
