package com.gdu.halbae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.halbae.domain.ReviewAttachDTO;
import com.gdu.halbae.domain.ReviewDTO;
import com.gdu.halbae.domain.ReviewLikeDTO;
import com.gdu.halbae.mapper.ReviewMapper;
import com.gdu.halbae.util.PageUtil;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }
    
    // 리뷰 작성
    @Override
    public void writeReview(ReviewDTO reviewDTO) {
        reviewMapper.insertReview(reviewDTO);
    }

    // 리뷰 첨부 파일 저장
    @Override
    public void saveReviewAttach(int reviewNo, String path, String originName, String fileName) {
        reviewMapper.insertReviewAttach(reviewNo, path, originName, fileName);
    }

    // 리뷰 포인트 업데이트
    @Override
    public void updateReviewPoint(ReviewDTO reviewDTO) {
        int point = (reviewDTO.getReviewAttachList() != null && !reviewDTO.getReviewAttachList().isEmpty()) ? 2000 : 500;
        reviewMapper.updateUserPoint(reviewDTO.getUserNo(), point);
    }

    // 리뷰 좋아요 정보 저장
    @Override
    public void insertReviewLike(ReviewLikeDTO reviewLikeDTO) {
        reviewMapper.insertReviewLike(reviewLikeDTO);
    }
    
    // 리뷰 첨부 파일 목록 가져오기
    @Override
    public List<ReviewAttachDTO> getReviewAttachList(int reviewNo) {
        return reviewMapper.getReviewAttachList(reviewNo);
    }
    
    // 리뷰 목록 조회
    @Override
    public List<ReviewDTO> getReviewList() {
        return reviewMapper.getReviewList();
    }

    // 리뷰 평균 평점 가져오기
    @Override
    public double getAverageRating() {
        return reviewMapper.getAverageRating();
    }

    // 리뷰 좋아요 수 증가 처리
    @Override
    public void increaseLikeCount(int reviewNo, int userNo) {
        reviewMapper.increaseLikeCount(reviewNo, userNo);
    }

    // 닉네임으로 사용자 번호 조회
    @Override
    public int getUserNoByUsername(String username) {
        return reviewMapper.getUserNoByUsername(username);
    }

    // 해당 회원이 리뷰를 좋아요 했는지 확인
    @Override
    public boolean hasLikedReview(int reviewNo, int userNo) {
        int count = reviewMapper.getLikeCountByUser(reviewNo, userNo);
        return count > 0;
    }

    // 페이징 처리된 리뷰 목록 가져오기
    @Override
    public List<ReviewDTO> getReviewListWithPaging(PageUtil criteria) {
        return reviewMapper.getReviewListWithPaging(criteria);
    }
    
    // 총 리뷰 수 가져오기
    @Override
    public int getTotalReviewCount() {
        return reviewMapper.getTotalReviewCount();
    }
    
}