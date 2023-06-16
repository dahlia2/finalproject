package com.gdu.halbae.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.halbae.service.WishService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/wish")
@Controller
public class WishController {
	
	// field
	private final WishService wishService;
	
	// 안녕
	
	// 찜목록 이동 페이지
	@GetMapping("/wish.html")
	public String wish() {
		return "classlist/wish";
	}
	
	// 찜목록 삽입
	@GetMapping("/wish.do")
	public String addWish(HttpServletRequest request, Model model) {
		model.addAttribute("wishResult", wishService.getWishByNo(request));
		System.out.println("찜목록 삽입 " + model);
		return "classlist/wish";
	}
	
	// 찜목록 삭제
	@GetMapping("/removeWish.do")
	public String removeWish(HttpServletRequest request, Model model) {
		model.addAttribute("removeResult", wishService.getWishRemoveByNo(request));
		System.out.println("찜목록 삭제 " + model);
		return "classlist/wish";
	}
	
	// 찜목록 리스트
	@GetMapping("/listWish.do")
	public String listWish(HttpServletRequest request, Model model) {
		wishService.getWishList(request, model);
		System.out.println("찜목록 리스트 리퀘스트 : " + request);
		System.out.println("찜목록 리스트 모델 : " + model);
		return "classlist/wish";
	}
	
	
	
	
	

}
