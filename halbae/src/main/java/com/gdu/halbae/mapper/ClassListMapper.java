package com.gdu.halbae.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.halbae.domain.ClassListDTO;
import com.gdu.halbae.domain.ImgClassDTO;

@Mapper
public interface ClassListMapper {
	public int getClassListCount(String classCategory);						// 전체/카테고리 클래스
	public List<ClassListDTO> selectClassList(Map<String, Object> map);		// 전체/카테고리 클래스
	public int getClassListCountAll();										// 전체 클래스
	public int getClassListCountNew();										// 최신 클래스
	public List<ClassListDTO> selectClassListNew(Map<String, Object> map);	// 최신 클래스
	public ImgClassDTO selectClassByNo(int classNo);						// 디테일 클래스
}
