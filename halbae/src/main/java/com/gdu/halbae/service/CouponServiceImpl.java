package com.gdu.halbae.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.CouponUserDTO;
import com.gdu.halbae.mapper.CouponMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
	
    private final CouponMapper couponMapper;
    
    @Override
    public List<CouponDTO> getAllCoupons() {
        // 모든 쿠폰 목록 조회
        return couponMapper.getAllCoupons();
    }
    
    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        LocalDateTime now = LocalDateTime.now();
        couponDTO.setCouponStartDate(now);
        couponDTO.setCouponEndDate(now.plusDays(30));
        // 쿠폰 생성
        couponMapper.createCoupon(couponDTO);
        return couponDTO;
    }
    
    @Override
    public void useCoupon(CouponUserDTO couponUserDTO) {
        couponUserDTO.setCouponStatus(1);
        // 쿠폰 사용
        couponMapper.useCoupon(couponUserDTO);
    }
    
}