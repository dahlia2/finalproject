package com.gdu.halbae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@GetMapping("/login.html")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/join.html")
	public String join() {
		return "user/join";
	}
}
