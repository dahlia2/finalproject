package com.gdu.halbae.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.gdu.halbae.domain.ImgClassDTO;

public interface ClassListService {
	public void getClassList(HttpServletRequest request, Model model);		// 전체/카테고리 클래스
	public void getClassListNew(HttpServletRequest request, Model model);	// 최신 클래스
	public ImgClassDTO getClassByNo(HttpServletRequest request);			// 디테일 클래스
}
