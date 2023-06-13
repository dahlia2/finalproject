package com.gdu.halbae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.halbae.service.PointService;

@Controller
public class PointController {
	
    @Autowired
    private PointService pointService;

    // 회원의 보유 포인트 조회
    @GetMapping("/point")
    public String getPoint(Model model, int userNo) {
        int userPoint = pointService.getUserPoint(userNo);
        model.addAttribute("userPoint", userPoint);
        return "point/pointForm";
    }
    
}