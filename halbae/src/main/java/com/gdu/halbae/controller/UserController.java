package com.gdu.halbae.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.halbae.domain.UserDTO;
import com.gdu.halbae.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/login.html")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/join.html")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/agree.html")
	public String agree(UserDTO userDTO, Model model, RedirectAttributes redirectAttributes) {
		String unqMsg = userService.checkUniqueId(userDTO);
		if(unqMsg.isEmpty()==false) {
//			model.addAttribute("unqMsg", unqMsg);
			redirectAttributes.addFlashAttribute("unqMsg", unqMsg);
			return "redirect:/user/join.html";
		}
		model.addAttribute("userDTO", userDTO);
		return "user/agree";
	}
	
	@PostMapping("/successJoin.do")
	public String successJoin(UserDTO userDTO) {
		System.out.println(userService.insertUser(userDTO));
		return "main";
	}
}
