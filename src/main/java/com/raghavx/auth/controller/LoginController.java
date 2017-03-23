package com.raghavx.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raghavx.auth.model.Billing;
import com.raghavx.auth.model.PasswordResetToken;
import com.raghavx.auth.model.User;
import com.raghavx.auth.repository.PasswordResetTokenRepository;
import com.raghavx.auth.repository.UserRepository;
import com.raghavx.auth.service.BillingService;
import com.raghavx.auth.service.MailService;
import com.raghavx.auth.service.UserService;

/**
 * The Class LoginController.
 */
@Controller
public class LoginController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The mail service. */
	@Autowired
	private MailService mailService;

	/** The password token repository. */
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The billing service. */
	@Autowired
	private BillingService billingService;

	/**
	 * Sign up.
	 *
	 * @param user the user
	 * @param bindingResult the binding result
	 * @param auth0 the auth 0
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String signUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Authentication auth0,
			Model model) {
		User existingMerchant = userService.findByUsername(user.getUsername());
		

		if (existingMerchant != null) {
			model.addAttribute("emailExistsError", "User is already registered with Email " + user.getUsername());
			return "redirect:/create";
		}
		
		user.setRoles(new String[]{"ROLE_USER"});
		
		user = userService.save(user);
		
		Billing billingEntity = new Billing();
		billingEntity.setUser(user);
		billingEntity.setAvailableDNDCredit(0l);
		billingService.save(billingEntity);

		mailService.send(null, user.getUsername());

		return "redirect:/create";
	}

	/**
	 * Creates the.
	 *
	 * @param model the model
	 * @return the string
	 */
	@Secured("ROLE_ADMIN")
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("user", new User());
		model.addAttribute("users", userService.findAll());

		return "create-new-user";
	}
	

	/**
	 * Home.
	 *
	 * @return the string
	 */
	@GetMapping("/home")
	public String home() {
		User user = new User();
		user.setUsername("r11@r.com");
		user.setPassword("1234");
		user.setRoles(new String[] { "ROLE_ADMIN", "ROLE_USER" });
		user = userService.save(user);
		
		Billing billingEntity = new Billing();
		billingEntity.setUser(user);
		billingEntity.setAvailableDNDCredit(1000000l);
		billingService.save(billingEntity);
		
		return "redirect:/";
	}

	/**
	 * Login.
	 *
	 * @param model the model
	 * @param auth the auth
	 * @return the string
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, Authentication auth) {

		if (auth != null && auth.isAuthenticated()) {
			return "redirect:/filter";
		}

		model.addAttribute("user", new User());
		return "login";
	}

	/**
	 * Login.
	 *
	 * @param authentication the authentication
	 * @return the string
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Authentication authentication) {
		return "redirect:/login";
	}

	/**
	 * Error.
	 *
	 * @return the string
	 */
	// @RequestMapping(value="/error")
	public String error() {
		return "error";
	}

	/**
	 * Reset password.
	 *
	 * @return the string
	 */
	@GetMapping("/resetpassword")
	public String resetPassword() {
		return "resetpassword";
	}

	/**
	 * Reset password.
	 *
	 * @param request the request
	 * @param userEmail the user email
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail, Model model) {

		User user = userService.findByUsername(userEmail);
		if (user != null) {

			userService.sendPasswordResetEmail(user);
			model.addAttribute("success", "Please check your email !!!");
		} else {
			model.addAttribute("error", "Invalid username!!!");
		}

		return "resetpassword";
	}
	

	/**
	 * Change password.
	 *
	 * @param token the token
	 * @param id the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/forgetpassword")
	public String changePassword(@RequestParam("token") String token, @RequestParam("id") Long id, Model model) {
		PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		if (passToken != null && passToken.getUser() != null) {
			if ((passToken.getExpiryDate() - System.currentTimeMillis()) > 0) {
				model.addAttribute("id", id);
				model.addAttribute("token", token);
				model.addAttribute("success", "success");
				return "changepassword";
			}
		}
		model.addAttribute("error", "Expired token!!");
		return "changepassword";
	}

	/**
	 * Change password.
	 *
	 * @param password the password
	 * @param confirmPassword the confirm password
	 * @param id the id
	 * @param token the token
	 * @param model the model
	 * @return the string
	 */
	@PostMapping("/forgetpassword")
	public String changePassword(@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, @RequestParam("id") Long id,
			@RequestParam("token") String token, Model model) {

		if (password.equals(confirmPassword)) {
			PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

			if (!passToken.getUser().getUserId().equals(id)) {
				return "invalid";
			}
			User user = userRepository.findOne(id);
			user.setPassword(password);

			passToken.setExpiryDate(0L);

			passwordTokenRepository.save(passToken);
			userRepository.save(user);

			model.addAttribute("success", "password changed successfully");

			return "changesuccess";
		}

		return "changepassword";
	}

}
