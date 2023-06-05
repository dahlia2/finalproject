package com.gdu.halbae.service;

import java.util.List;

import com.gdu.halbae.domain.ReviewDTO;

public interface ReviewService {
	public List<ReviewDTO> getReviewList(int reviewNo);
}