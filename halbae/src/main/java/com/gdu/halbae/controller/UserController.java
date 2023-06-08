package com.gdu.halbae.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	// 이전 주소를 저장한 상태로 login 페이지로 이동
	@GetMapping("/login.html")
	public String login(@RequestHeader("referer") String url, Model model) {
		model.addAttribute("url", url);
		System.out.println(url);
		return "user/login";
	}
	
	// 회원 가입
	@GetMapping("/join.html")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/agree.html")
	public String agree(UserDTO userDTO, Model model, RedirectAttributes redirectAttributes) {
		String unqMsg = userService.checkUniqueId(userDTO);
		if(unqMsg.isEmpty()==false) {
			redirectAttributes.addFlashAttribute("unqMsg", unqMsg);
			return "redirect:/user/join.html";
		}
		model.addAttribute("userDTO", userDTO);
		return "user/agree";
	}
	
	@PostMapping("/successJoin.do")
	public String successJoin(UserDTO userDTO) {
		userService.insertUser(userDTO);
		return "user/successjoin";
	}
	
	// 로그인
	@PostMapping("/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}
	
	
}
