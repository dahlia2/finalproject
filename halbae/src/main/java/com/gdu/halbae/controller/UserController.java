package com.gdu.halbae.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.halbae.domain.UserDTO;
import com.gdu.halbae.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
	
	private final UserService userService;
	
	// 이전 주소를 저장한 상태로 login 페이지로 이동
	@GetMapping("/login.html")
	public String login() {
		return "user/login";
	}
	
	// 회원 가입
	@GetMapping("/join.html")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/agree.html")
	public String agree(UserDTO userDTO, Model model, RedirectAttributes redirectAttributes) {
		String unqMsg = "";
					 unqMsg += userService.checkUniqueId(userDTO);
					 unqMsg += userService.checkIdCountByTel(userDTO);
		if(unqMsg.isEmpty()==false) {
			redirectAttributes.addFlashAttribute("unqMsg", unqMsg);
			return "redirect:/user/join.html";
		}
		model.addAttribute("userDTO", userDTO);
		return "user/agree";
	}
	
	@PostMapping("/successJoin.do")
	public String successJoin(UserDTO userDTO) {
		userService.insertUser(userDTO);
		return "user/successjoin";
	}
	
	// 로그인
	@PostMapping("/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}
	
	// 로그아웃
	@PostMapping("/logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
			userService.logout(request, response);
	}
	
	// 아이디 찾기
	@GetMapping("/findId.html")
	public String toFindId() {
		return "user/findId";
	}
	// 가입한 아이디 조회 후 내역 보여주기
	@PostMapping("/findId.do")
	public String findId(String userTel, Model model) {
		model.addAttribute("userDTOList",userService.selectUserIdByTel(userTel));
		return "user/findIdResult";
	}
	// 비밀번호 찾기
	@GetMapping("/findPw.html")
	public String toFindPw() {
		return "user/findPw";
	}
	// 인증코드 전송하기
	@ResponseBody
	@GetMapping(value="/sendCode.do", produces="application/json")
	public Map<String, Object> sendCode(String email) {
		return userService.sendCode(email);
	}
	
	// 임시 비밀번호 전송하기
	@ResponseBody
	@GetMapping(value="/sendTempPw.do", produces="application/json")
	public Map<String, Object> sendTempPw(String email) {
		return userService.sendTempPw(email);
	}
	
	// 전송된 임시 비번으로 유저 비번 바꾸기
	@PostMapping("/tempPw.do")
	public void updateTempPw(UserDTO userDTO, HttpServletResponse response) {
		userService.updateTempPw(userDTO, response);
	}
	
	// 마이 프로필로 이동
	@PostMapping("/myProfile.html")
	public String myProfile(String loginId, Model model) {
		model.addAttribute("userDTO", userService.selectUserProfile(loginId));
		return "user/myprofile";
	}
	// 프로필 사진 바꾸기
	@ResponseBody
	@PostMapping(value="/updateProfile.do", produces="application/json")
	public ResponseEntity<byte[]> updateProfile(MultipartHttpServletRequest multipartRequest) {
		System.out.println("컨트롤러 : " + userService.updateProfile(multipartRequest));
		return	userService.updateProfile(multipartRequest);
	}
	
	// 이름(별명)바꾸기
	@PostMapping("/modifyName.do")
	public void modifyName(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
		userService.modifyName(userDTO, request, response);
	}
	// 비번 바꾸기로 이동
	@PostMapping("/modifyPw.html")
	public String ToModifyPw(String userId, Model model)  {
		model.addAttribute("userId", userId);
		return "user/modifyPw";
	}
	
	// 현재 비밀번호 확인
	@ResponseBody
	@PostMapping(value="/confirmPw.do", produces="application/json")
	public Map<String, Object> confirmPw(UserDTO userDTO) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("confirmResult", userService.confirmPw(userDTO));
		
		return map;
	}
	
	// 비밀번호 변경하기
	@PostMapping("/modifyPw.do")
	public void modifyPw(HttpServletRequest request, HttpServletResponse response) {
		userService.updateUserPwById(request, response);
	}
	
	// 회원 탈퇴
	@PostMapping("/deleteUser.do")
	public void deleteAccount(HttpServletRequest request, HttpServletResponse response) {
		userService.deleteUser(request, response);
	}
	
}
