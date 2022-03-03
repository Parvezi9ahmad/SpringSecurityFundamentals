package com.bharath.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "Spring Security Rocks!!";
		
	}
	
	@GetMapping("/bye")
	public String bye() {
		return "Bye its will see you tomorrow";
	}

}
