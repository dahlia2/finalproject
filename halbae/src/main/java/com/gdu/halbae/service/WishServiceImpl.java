package com.gdu.halbae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.WishDTO;
import com.gdu.halbae.mapper.WishMapper;
import com.gdu.halbae.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WishServiceImpl implements WishService {
	
	// field
	private final WishMapper wishMapper;
	private final PageUtil pageUtil;
	
	// 찜목록 삽입
	@Override
	public int getWishByNo(HttpServletRequest request) {
		
		String strClassNo = request.getParameter("classNo");
		int classNo = 0;
		if(strClassNo != null && strClassNo.isEmpty() == false) {
			classNo = Integer.parseInt(strClassNo);
		}
		
		HttpSession session = request.getSession();
		int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
		
		WishDTO wishDTO = new WishDTO();
		wishDTO.setClassNo(classNo);
		wishDTO.setUserNo(userNo);
		
		int wishResult = wishMapper.insertWishByNo(wishDTO);
		
		return wishResult;
	}
	
	
	// 찜목록 삭제
	@Override
	public int getWishRemoveByNo(HttpServletRequest request) {
		
		String strClassNo = request.getParameter("classNo");
		int classNo = 0;
		if(strClassNo != null && strClassNo.isEmpty() == false) {
			classNo = Integer.parseInt(strClassNo);
		}
		
		HttpSession session = request.getSession();
		int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
		
		WishDTO wishDTO = new WishDTO();
		wishDTO.setClassNo(classNo);
		wishDTO.setUserNo(userNo);
		
		int removeResult = wishMapper.removeWishByNo(wishDTO);
		
		return removeResult;
	}
	
	
	// 찜목록 리스트
	@Override
	public void getWishList(HttpServletRequest request, Model model) {
		
		String strClassNo = request.getParameter("classNo");
		int classNo = 0;
		if(strClassNo != null && strClassNo.isEmpty() == false) {
			classNo = Integer.parseInt(strClassNo);
		}
		
		HttpSession session = request.getSession();
		int userNo = Integer.parseInt(session.getAttribute("userNo").toString());
		
		int wishCount = wishMapper.getWishListCount(userNo);
		
		// userNo를 mapper에 주고 classNo를 전부 뽑아오는 코드
		List<Integer> wishList = wishMapper.selectClassNoInWish(userNo);
		List<ClassListDTO> classList = wishMapper.selectWishList(wishList);
		
		// select -> option 태그에 카테고리 별 개수 구하는 코드
		model.addAttribute("classList", classList);
		model.addAttribute("wishCount", wishCount);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
