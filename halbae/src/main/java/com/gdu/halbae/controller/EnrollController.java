package com.gdu.halbae.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.halbae.service.EnrollService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/enroll")
@RequiredArgsConstructor
@Controller
public class EnrollController {

	private final EnrollService enrollService;

	/* 신청 페이지 */
	// 클래스 정보와, 스케줄 정보 뿌림
	@RequestMapping("/apply.page")
	public String applyPage(HttpServletRequest request, Model model) {
		model.addAttribute("classList", enrollService.classListByNo(request));
		model.addAttribute("scheduleList", enrollService.scheduleByClassNo(request));
		model.addAttribute("user", enrollService.selectUserByNo(request));
		return "enroll/apply";
	}
	
	/* 결제 페이지 */
	@RequestMapping("/payment.page")
	public String payPage(HttpSession session, Model model) {
		model.addAttribute("classList", session.getAttribute("classList"));
		model.addAttribute("userCoupon", session.getAttribute("userCoupon"));
		session.getAttribute("enrollPerson");
		return "enroll/payment";
	}
	
	
	// 수강 신청
	@PostMapping("/apply.do")
	public String applyClass(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("applyResult", enrollService.applyClass(request, redirectAttributes));

	    session.setAttribute("classList", enrollService.classListByNo(request));
	    session.setAttribute("userCoupon", enrollService.couponListByNo(request));
	    session.setAttribute("enrollPerson", request.getParameter("enrollPerson"));
	    
	    System.out.println("클래스조회 : " + enrollService.classListByNo(request));
	    System.out.println("쿠폰조회 : " + enrollService.couponListByNo(request));
		return "redirect:/enroll/payment.page";
	}
}
