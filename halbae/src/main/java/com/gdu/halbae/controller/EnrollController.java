package com.gdu.halbae.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/enroll")
@Controller
public class EnrollController {
	
	// 클래스 신청폼
	@RequestMapping("/apply.page")
	public String applyPage(HttpServletRequest requst, Model model) {
		return "applyForm";
	}
	
	


	
}
