package com.gdu.halbae.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.CouponUserDTO;

@Mapper
public interface CouponMapper {
    // 모든 쿠폰 목록 조회
    List<CouponDTO> getAllCoupons();
    // 쿠폰 생성
    void createCoupon(CouponDTO couponDTO);
    // 쿠폰 사용
    void useCoupon(CouponUserDTO couponUserDTO);
}