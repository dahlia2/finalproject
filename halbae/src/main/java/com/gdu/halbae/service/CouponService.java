package com.gdu.halbae.service;

import java.util.List;

import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.CouponUserDTO;

public interface CouponService {
    // 모든 쿠폰 목록 조회
    List<CouponDTO> getAllCoupons();
    // 쿠폰 생성
    CouponDTO createCoupon(CouponDTO couponDTO);
    // 쿠폰 사용
    void useCoupon(CouponUserDTO couponUserDTO);
}