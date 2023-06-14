package com.gdu.halbae.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.halbae.domain.UserDTO;

public interface UserService {
	//회원가입
	public String checkUniqueId(UserDTO userDTO);
	public String checkIdCountByTel(UserDTO userDTO);
	public int insertUser(UserDTO userDTO);
	//로그인
	public void login(HttpServletRequest request, HttpServletResponse response);
	public void autoLogin(HttpServletRequest request, HttpServletResponse response);
	//로그아웃
	public void logout(HttpServletRequest request, HttpServletResponse response);
	//인증코드 보내기
	public Map<String, Object> sendCode(String email);
	//임시 비번 보내기
	public Map<String, Object> sendTempPw(String email);
	//임시 비번으로 비번 바꾸기
	public void updateTempPw(UserDTO userDTO, HttpServletResponse response);
	//아이디 찾기
	public List<UserDTO> selectUserIdByTel(String userTel);
	//프로필 정보 가져오기
	public UserDTO selectUserProfile(String loginId);
	//프로필 사진 변경하기
	public Map<String, Object> updateProfile(MultipartHttpServletRequest multipartRequest);
	public ResponseEntity<byte[]> displayProfile(String userImgPath);
	//이름 변경하기
	public void modifyName(UserDTO userDTO, HttpServletRequest request, HttpServletResponse response);
	//비번 변경전 현재 비번 확인
	public boolean confirmPw(UserDTO userDTO);
	// 비번 변경하기
	public void updateUserPwById(HttpServletRequest request, HttpServletResponse response);
	// 회원 탈퇴
	public void deleteUser(HttpServletRequest request, HttpServletResponse response);
}
