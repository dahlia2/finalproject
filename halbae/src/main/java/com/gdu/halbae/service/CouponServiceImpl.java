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
    
    // 모든 쿠폰 목록 조회
    @Override
    public List<CouponDTO> getAllCoupons() {
        return couponMapper.getAllCoupons();
    }
    
    // 회원의 보유 쿠폰 수 조회
    @Override
    public int getAvailableCouponCount() {
        return couponMapper.getAvailableCouponCount();
    }
    
    // 쿠폰 생성
    @Override
    public CouponDTO createCoupon(CouponDTO couponDTO) {
        LocalDateTime now = LocalDateTime.now();
        couponDTO.setCouponStartDate(now);
        couponDTO.setCouponEndDate(now.plusDays(30));
        couponMapper.createCoupon(couponDTO);
        return couponDTO;
    }
    
    // 쿠폰 사용
    @Override
    public void useCoupon(CouponUserDTO couponUserDTO) {
        couponUserDTO.setCouponStatus(1);
        couponMapper.useCoupon(couponUserDTO);
    }
    
}