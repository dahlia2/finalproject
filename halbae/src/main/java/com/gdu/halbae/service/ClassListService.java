package com.gdu.halbae.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.ImgClassDTO;

public interface ClassListService {
	public void getClassList(HttpServletRequest request, Model model);		// 전체/카테고리 클래스
	public void getClassListNew(HttpServletRequest request, Model model);	// 최신 클래스
	public ImgClassDTO getClassByNo(HttpServletRequest request);			// 디테일 클래스
	
	public int addClass(MultipartHttpServletRequest multipartHttpServletRequest);	// 클래스 등록(파일)
	public ResponseEntity<byte[]> display(int classNo);								// 클래스 등록한 이미지 뽑는 메소드 
	public void getClassUploadList(HttpServletRequest request, Model model);		// 클래스 등록 목록
}
