package com.gdu.halbae.service;

import java.util.List;

import com.gdu.halbae.domain.ReviewAttachDTO;
import com.gdu.halbae.domain.ReviewDTO;
import com.gdu.halbae.domain.ReviewLikeDTO;
import com.gdu.halbae.util.PageUtil;

public interface ReviewService {
	
	// 리뷰 작성
    void writeReview(ReviewDTO reviewDTO);
    void saveReviewAttach(int reviewNo, String path, String originName, String fileName);
    void updateReviewPoint(ReviewDTO reviewDTO);
    void insertReviewLike(ReviewLikeDTO reviewLikeDTO);
    List<ReviewAttachDTO> getReviewAttachList(int reviewNo);
    
    // 리뷰 목록
    List<ReviewDTO> getReviewList();
    double getAverageRating();
    void increaseLikeCount(int reviewNo, int userNo);
    int getUserNoByUsername(String username);
    boolean hasLikedReview(int reviewNo, int userNo);
    List<ReviewDTO> getReviewListWithPaging(PageUtil criteria);
    int getTotalReviewCount();
    
}