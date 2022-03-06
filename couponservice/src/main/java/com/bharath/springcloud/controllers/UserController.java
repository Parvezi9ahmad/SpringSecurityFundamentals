package com.bharath.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repos.UserRepo;
import com.bharath.springcloud.security.SecurityService;
import com.bharath.springcloud.security.SecurityServiceImpl;

@Controller
public class UserController {
 
	@Autowired
	private SecurityService securityService;
	
	/*
	 * @Autowired private PasswordEncoder encoder;
	 * 
	 * @Autowired private UserRepo repo;
	 */
	
	
	
	
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/showReg")
	public String showRegistrationPage() {
		return "registerUser";

	}

	@PostMapping("/registerUser")
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "login";
	}

	@GetMapping("/")
	public String showLoginPage() {
		return "login";
	}
	
	/*
	 * @GetMapping("/showReg") public String showRegistrationPage() { return
	 * "registerUser"; }
	 * 
	 * @PostMapping("/registerUser") public String register(User user) {
	 * user.setPassword(encoder.encode(user.getPassword())); repo.save(user); return
	 * "login";
	 * 
	 * }
	 */
	
	@PostMapping("/login")
	public String login(String email, String password) {
		boolean result = securityService.login(email, password);
		if(result) {
			return "index";
		}
		return "login";
		
	}
	
	
}
