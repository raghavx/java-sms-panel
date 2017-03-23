package com.raghavx.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.raghavx.auth.model.User;
import com.raghavx.auth.service.UserService;

@Controller
public class EditUserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/edit")
	public String search(Model model) {
		model.addAttribute("editUserUO", new EditUserUO());
		return "edit-user";
	}

	@PostMapping("/edit")
	public String search(@ModelAttribute EditUserUO editUserUO, Model model, Authentication auth) {
		
		User user = (User)auth.getPrincipal();
		
		user  = userService.findByUsername(user.getUsername());
		
		user.setPassword(editUserUO.getPassword());
		user.setCredit(user.getCredit()+editUserUO.getIncrementBy());
		
		//user.set
		
		model.addAttribute("editUserUO", new EditUserUO());
		return "edit-user";
	}
}
