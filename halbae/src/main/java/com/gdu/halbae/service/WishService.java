package com.gdu.halbae.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface WishService {
	
	public int getWishByNo(HttpServletRequest request);					// 찜목록 삽입
	public int getWishRemoveByNo(HttpServletRequest request);			// 찜목록 삭제
	public void getWishList(HttpServletRequest request, Model model);	// 찜목록 리스트

}
