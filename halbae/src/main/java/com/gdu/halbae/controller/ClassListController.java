package com.gdu.halbae.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.halbae.service.ClassListService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/class")
@Controller
public class ClassListController {
	
	// field
	private final ClassListService classListService;
	
	// 찜목록 이동 페이지
	@GetMapping("/wish.html")
	public String wish() {
		return "classlist/wish";
	}
	
	
	// 전체/카테고리 클래스
	@GetMapping("/classList.do")
	public String list(HttpServletRequest request, Model model) {
		classListService.getClassList(request, model);
		return "classlist/classlist";
	}
	
	// 최신 클래스
	@GetMapping("/classListNew.do")
	public String listNew(HttpServletRequest request, Model model) {
		classListService.getClassListNew(request, model);
		return "classlist/classlist";
	}
	
	// 디테일 클래스
	@GetMapping("/classDetail.do")
	public String listDetail(HttpServletRequest request, Model model) {
		model.addAttribute("imgClass", classListService.getClassByNo(request));
		return "classlist/classdetail";
	}
	
	
	
	

}
