package com.gdu.halbae.service;

import java.util.List;

import com.gdu.halbae.domain.ReviewAttachDTO;
import com.gdu.halbae.domain.ReviewDTO;
import com.gdu.halbae.domain.ReviewLikeDTO;
import com.gdu.halbae.util.PageUtil;

public interface ReviewService {
    // 리뷰 작성
    void writeReview(ReviewDTO reviewDTO);
    // 리뷰 첨부파일 저장
    void saveReviewAttach(int reviewNo, String path, String originName, String fileName);
    // 리뷰 평점 업데이트
    void updateReviewPoint(ReviewDTO reviewDTO);
    // 리뷰 첨부파일 목록 조회
    List<ReviewAttachDTO> getReviewAttachList(int reviewNo);
    // 리뷰 좋아요 추가
    void insertReviewLike(ReviewLikeDTO reviewLikeDTO);
    // 리뷰 목록 조회
    List<ReviewDTO> getReviewList();
    // 전체 리뷰 평균 평점 조회
    double getAverageRating();
    // 리뷰 좋아요 수 증가
    void increaseLikeCount(int reviewNo, int userNo);
    // 사용자명으로 사용자 번호 조회
    int getUserNoByUsername(String username);
    // 특정 사용자가 특정 리뷰에 좋아요를 눌렀는지 확인하는 기능
    boolean hasLikedReview(int reviewNo, int userNo);
    // 페이징 처리된 리뷰 목록 조회
    List<ReviewDTO> getReviewListWithPaging(PageUtil criteria);
    // 전체 리뷰 개수 조회
    int getTotalReviewCount();
}