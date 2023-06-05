package com.gdu.halbae.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.halbae.domain.ReviewDTO;
import com.gdu.halbae.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
  
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public  List<ReviewDTO> getReviewList(int prodNo) {
        return reviewMapper.getReviewList(prodNo);
    }
    
}