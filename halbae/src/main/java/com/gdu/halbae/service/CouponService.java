package com.gdu.halbae.service;

import java.util.List;

import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.CouponUserDTO;

public interface CouponService {
    // 모든 쿠폰 목록 조회
    List<CouponDTO> getAllCoupons();
    // 회원의 보유 쿠폰 수 조회
    int getUserCouponCount(int userNo);
    // 쿠폰 생성
    CouponDTO createCoupon(CouponDTO couponDTO);
    // 쿠폰 사용
    void useCoupon(CouponUserDTO couponUserDTO);
}