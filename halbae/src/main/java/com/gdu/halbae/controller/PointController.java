package com.gdu.halbae.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.halbae.service.PointService;

@Controller
public class PointController {
	
    @Autowired
    private PointService pointService;

    @GetMapping("/user/point")
    public String getUserPoint(Model model, HttpSession session) {
        int userNo = (int) session.getAttribute("userNo"); // 세션에서 사용자 번호 가져오기
        int userPoint = pointService.getUserPoint(userNo); // 사용자 포인트 가져오기
        model.addAttribute("userPoint", userPoint); // userPoint 속성을 모델에 추가
        return "point/pointForm"; // pointForm.html 템플릿 이름 반환
    }
    
}