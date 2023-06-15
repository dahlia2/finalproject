package com.gdu.halbae.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
    
    // 회원이 보유한 쿠폰 목록과 보유 쿠폰 수 조회
    @GetMapping("/coupon")
    public String getCouponPage(Model model, HttpSession session, @RequestParam(value = "userNo", required = false) Integer userNo) {
        if (userNo == null) {
            userNo = (int) session.getAttribute("userNo");
        }
        List<CouponDTO> userCoupons = couponService.getAllCoupons(userNo);
        int couponCount = couponService.getAvailableCouponCount(userNo);
        model.addAttribute("coupons", userCoupons);
        model.addAttribute("couponCount", couponCount);
        return "coupon/couponForm";
    }

    
    // 쿠폰 등록
    @PostMapping("/issueCoupon")
    @ResponseBody
    public String issueCoupon(@RequestBody CouponDTO couponDTO, HttpSession session) {
        int userNo = (int) session.getAttribute("userNo");
        couponService.issueCouponToUser(couponDTO, userNo);
        return "success";
    }
    
    // 쿠폰 사용
    @PostMapping("/coupon/use")
    @ResponseBody
    public void useCoupon(@RequestBody CouponUserDTO couponUserDTO) {
        couponService.useCoupon(couponUserDTO);
    }
    
}