package com.gdu.halbae.service;

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
    
    // 회원이 보유한 쿠폰 목록 조회
    @Override
    public List<CouponDTO> getAllCoupons(int userNo) {
        return couponMapper.getAllCoupons(userNo);
    }
    
    // 회원의 보유 쿠폰 수 조회
    @Override
    public int getAvailableCouponCount(int userNo) {
        return couponMapper.getAvailableCouponCount(userNo);
    }

    // 쿠폰 등록
    @Override
    public void issueCouponToUser(CouponDTO couponDTO, int userNo) {
        String couponName = couponDTO.getCouponName();
        int couponNo = couponMapper.getCouponNoByCouponName(couponName);
        CouponUserDTO couponUserDTO = new CouponUserDTO();
        couponUserDTO.setUserNo(userNo);
        couponUserDTO.setCouponNo(couponNo);
        couponUserDTO.setCouponStatus(1);
        couponMapper.insertCouponUser(couponUserDTO);
    }

    // 쿠폰명을 기준으로 쿠폰 번호를 조회
    @Override
    public int getCouponNoByCouponName(String couponName) {
        return couponMapper.getCouponNoByCouponName(couponName);
    }
    
    // 쿠폰 사용
    @Override
    public void useCoupon(CouponUserDTO couponUserDTO) {
        couponUserDTO.setCouponStatus(1);
        couponMapper.useCoupon(couponUserDTO);
    }
    
}