package com.abc.termproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/customer")
	public String customer() {
		return "customer";
	}
}
