package com.gdu.halbae.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.halbae.domain.CouponDTO;
import com.gdu.halbae.domain.CouponUserDTO;
import com.gdu.halbae.service.CouponService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CouponController {
	
    private final CouponService couponService;
    
    // 모든 쿠폰 목록 조회
    @GetMapping("/coupon")
    public String getCouponPage(Model model) {
        model.addAttribute("coupons", couponService.getAllCoupons());
        return "coupon/couponForm";
    }
    
    // 회원의 보유 쿠폰 수 조회
    @GetMapping("/coupons")
    public String getCoupons(@RequestParam("userNo") int userNo, Model model) {
        int couponCount = couponService.getUserCouponCount(userNo);
        model.addAttribute("couponCount", couponCount);
        return "coupon/coupon";
    }
    
    // 쿠폰 생성
    @PostMapping("/coupon")
    @ResponseBody
    public CouponDTO createCoupon(@RequestBody CouponDTO couponDTO) {
        return couponService.createCoupon(couponDTO);
    }
    
    // 쿠폰 사용
    @PostMapping("/coupon/use")
    @ResponseBody
    public void useCoupon(@RequestBody CouponUserDTO couponUserDTO) {
        couponService.useCoupon(couponUserDTO);
    }
    
}