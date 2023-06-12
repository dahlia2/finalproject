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
    
    // 리뷰 작성
    void insertReview(ReviewDTO reviewDTO);
    void insertReviewAttach(@Param("reviewNo") int reviewNo,
                            @Param("path") String path,
                            @Param("originName") String originName,
                            @Param("fileName") String fileName);
    void updateUserPoint(@Param("userNo") int userNo, @Param("point") int point);
    void insertReviewLike(ReviewLikeDTO reviewLikeDTO);
    List<ReviewAttachDTO> getReviewAttachList(int reviewNo);
    
    // 리뷰 목록
    List<ReviewDTO> getReviewList();
    double getAverageRating();
    void increaseLikeCount(int reviewNo, int userNo);
    int getUserNoByUsername(String username);
    int getLikeCountByUser(int reviewNo, int userNo);
    
    // 리뷰 목록 페이징
    List<ReviewDTO> getReviewListWithPaging(@Param("criteria") PageUtil criteria);
    int getTotalReviewCount();
    
}