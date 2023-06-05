package com.gdu.halbae.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.halbae.domain.ReviewDTO;

@Mapper
public interface ReviewMapper {
	public List<ReviewDTO> getReviewList(int prodNo);
}