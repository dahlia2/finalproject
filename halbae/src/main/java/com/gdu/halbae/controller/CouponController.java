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
    
    @GetMapping("/coupon")
    public String getCouponPage(Model model) {
        model.addAttribute("coupons", couponService.getAllCoupons());
        return "coupon/couponForm";
    }
    
    @GetMapping("/coupons")
    public String getCoupons(@RequestParam("userNo") int userNo, Model model) {
        // 회원의 보유 쿠폰 수 조회
        int couponCount = couponService.getUserCouponCount(userNo);
        model.addAttribute("couponCount", couponCount);
        return "coupon/coupon";
    }

    
    @PostMapping("/coupon")
    @ResponseBody
    public CouponDTO createCoupon(@RequestBody CouponDTO couponDTO) {
        // 쿠폰 생성
        return couponService.createCoupon(couponDTO);
    }
    
    @PostMapping("/coupon/use")
    @ResponseBody
    public void useCoupon(@RequestBody CouponUserDTO couponUserDTO) {
        // 쿠폰 사용
        couponService.useCoupon(couponUserDTO);
    }
    
}