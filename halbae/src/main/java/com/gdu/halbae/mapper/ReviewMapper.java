package com.gdu.halbae.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gdu.halbae.domain.ReviewAttachDTO;
import com.gdu.halbae.domain.ReviewDTO;
import com.gdu.halbae.domain.ReviewLikeDTO;
import com.gdu.halbae.util.PageUtil;

@Mapper
public interface ReviewMapper {
    // 리뷰 등록
    void insertReview(ReviewDTO reviewDTO);
    // 리뷰 첨부파일 등록
    void insertReviewAttach(@Param("reviewNo") int reviewNo,
                            @Param("path") String path,
                            @Param("originName") String originName,
                            @Param("fileName") String fileName);
    // 사용자 포인트 업데이트
    void updateUserPoint(@Param("userNo") int userNo, @Param("point") int point);
    // 리뷰 좋아요 등록
    void insertReviewLike(ReviewLikeDTO reviewLikeDTO);
    // 리뷰 첨부파일 목록 조회
    List<ReviewAttachDTO> getReviewAttachList(int reviewNo);
    // 리뷰 목록 조회
    List<ReviewDTO> getReviewList();
    // 전체 리뷰 평균 평점 조회
    double getAverageRating();
    // 리뷰 좋아요 수 증가
    void increaseLikeCount(int reviewNo, int userNo);
    // 사용자명으로 사용자 번호 조회
    int getUserNoByUsername(String username);
    // 사용자가 특정 리뷰에 좋아요를 눌렀는지 조회
    int getLikeCountByUser(int reviewNo, int userNo);
    // 페이징 처리된 리뷰 목록 조회
    List<ReviewDTO> getReviewListWithPaging(@Param("criteria") PageUtil criteria);
    // 전체 리뷰 개수 조회
    int getTotalReviewCount();
}