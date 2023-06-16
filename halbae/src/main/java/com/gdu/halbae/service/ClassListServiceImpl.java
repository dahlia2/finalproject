package com.gdu.halbae.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.ImgClassDTO;
import com.gdu.halbae.mapper.ClassListMapper;
import com.gdu.halbae.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClassListServiceImpl implements ClassListService {
	
	// field
	private final ClassListMapper classListMapper;
	private final PageUtil pageUtil;
	
	// 전체,카테고리 클래스
	@Override
	public void getClassList(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		String classCategory = request.getParameter("classCategory");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classCategory", classCategory);
		
		int totalRecord = classListMapper.getClassListCount(classCategory);
		
		int recordPerPage = 12;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);
		
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<ClassListDTO> classList = classListMapper.selectClassList(map);
		
		// select -> option 태그에 카테고리 별 개수 구하는 코드
		model.addAttribute("classCountAll", classListMapper.getClassListCountAll());
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("classList", classList);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/class/classList.do?classCategory=" + classCategory));
		model.addAttribute("categoryName", classCategory);
		
	}
	
	
	
	// 최신 클래스
	@Override
	public void getClassListNew(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		int totalRecord = classListMapper.getClassListCountNew();
		
		int recordPerPage = 12;
		
		pageUtil.setPageUtil(page, totalRecord, recordPerPage);	// begin이랑 end값을 구해서 mapper에 전달하기위한 코드 필수!
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("recordPerPage", recordPerPage);
		
		List<ClassListDTO> classList = classListMapper.selectClassListNew(map);
		
		
		model.addAttribute("newClass", "최신");
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("classList", classList);
		model.addAttribute("beginNo", totalRecord - (page - 1) * recordPerPage);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/class/classListNew.do"));
		
	}
	
	// 디테일 클래스
	@Override
	public ImgClassDTO getClassByNo(HttpServletRequest request) {
		
		String strClassNo = request.getParameter("classNo");
		int classNo = 0;	// null, 빈문자열이 올때 0값을 사용하기위한 선언이다!
		if(strClassNo != null && strClassNo.isEmpty() == false) { // 자바는 빈문자열 처리시 .isEmpty()를 사용한다. (기본은 비어있다라는 말!)
			classNo = Integer.parseInt(strClassNo);
		}
		return classListMapper.selectClassByNo(classNo);
	}
	
	// 찜목록 클래스
	@Override
	public ClassListDTO getWishClassByNo(HttpServletRequest request) {
		
		String strClassNo = request.getParameter("classNo");
		int classNo = 0;
		if(strClassNo != null && strClassNo.isEmpty() == false) {
			classNo = Integer.parseInt(strClassNo);
		}
		return classListMapper.selectWishClassByNo(classNo);
	}
	

}
