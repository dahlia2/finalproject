package com.gdu.halbae.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.halbae.domain.UserDTO;

public interface UserService {
	//회원가입
	public String checkUniqueId(UserDTO userDTO);
	public int insertUser(UserDTO userDTO);
	//로그인
	public void login(HttpServletRequest request, HttpServletResponse response);
	public void autoLogin(HttpServletRequest request, HttpServletResponse response);
}
