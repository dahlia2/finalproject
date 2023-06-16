package com.gdu.halbae.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.ScheduleDTO;
import com.gdu.halbae.domain.UserDTO;

public interface EnrollService {
	
	// 단순 강의 조회
	public ClassListDTO classListByNo(HttpServletRequest request);
	
	// 스케줄 조회
	public List<ScheduleDTO> scheduleByClassNo(HttpServletRequest request);
	
	// 쿠폰 조회
	public List<CouponDTO> couponListByNo(HttpServletRequest request);
	
	// 회원 조회
	public UserDTO selectUserByNo(HttpServletRequest request);
	
	// 수강 신청
	public int applyClass(HttpServletRequest request, RedirectAttributes redirectAttributes);
	
	
}
