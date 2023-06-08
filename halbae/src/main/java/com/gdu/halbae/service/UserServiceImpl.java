package com.gdu.halbae.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.gdu.halbae.domain.UserDTO;
import com.gdu.halbae.mapper.UserMapper;
import com.gdu.halbae.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	
	private final SecurityUtil securityUtil;
	private final UserMapper userMapper;
	
	// 회원가입
	@Override
	public String checkUniqueId(UserDTO userDTO) {
		String msg = "";
		if(userMapper.checkUniqueId(userDTO.getUserId()) != null) {
			msg="중복된 이메일입니다. 다른 이메일을 사용해주세요.";
			return msg;
		}
		return msg;
	}
	
	@Override
	public int insertUser(UserDTO userDTO) {
		String userName = userDTO.getUserName();
		String userTel = userDTO.getUserTel();
		String userId = userDTO.getUserId();
		String userPw = userDTO.getUserPw();
		
		// 스크립트 방지
		userName = securityUtil.preventXSS(userName);
		userTel = securityUtil.preventXSS(userTel);
		userId = securityUtil.preventXSS(userId);
		userPw = securityUtil.preventXSS(userPw);
		// 비번 암호화
		userPw = securityUtil.getSha256(userPw);
		// 보안 처리 완료한 정보로 바꾸기
		userDTO.setUserName(userName);
		userDTO.setUserTel(userTel);
		userDTO.setUserId(userId);
		userDTO.setUserPw(userPw);
		
		System.out.println(userId);
		
		return userMapper.insertUser(userDTO);
	}
	
	// 로그인
	@Override
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("url");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		userPw = securityUtil.getSha256(userPw);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("userId", userId);
		map.put("userPw", userPw);
		
		UserDTO userDTO = userMapper.selectLoginInfo(map);
		
		
		
		if(userDTO != null) {
			// 자동 로그인 처리하기
			autoLogin(request, response);
			HttpSession session = request.getSession();
			// 세션에 정보 저장
			session.setAttribute("loginId", userId);
			session.setAttribute("userNo", userDTO.getUserNo());
			
			// 로그인하고 로그인으로 접속한 url로 이동
			try {
				System.out.println(url);
				response.sendRedirect(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			
			try {
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('이메일과 비밀번호를 확인해주세요.');");
		    out.println("location.href='" + request.getContextPath() + "/user/login.html'");
		    out.println("</script>");
		    out.flush();
		    out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		}
		
	}
	
	// 자동 로그인
	@Override
	public void autoLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String chkAutoLogin = request.getParameter("chkAutoLogin");
		
		if(chkAutoLogin != null) {
			HttpSession session = request.getSession();
			String sessionId = session.getId();
			
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(userId);
			userDTO.setUserAutoLoginId(sessionId);
			userDTO.setUserAutoLoginExpired(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 15))); // 15일
			
			userMapper.insertAutoLogin(userDTO);
			
			Cookie cookie = new Cookie("autoLoginId", sessionId);
			cookie.setMaxAge(60 * 60 * 24 * 15); // 15일
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			
			
		}else {
			userMapper.deleteAutoLogin(userId);
			
			Cookie cookie = new Cookie("autoLoginId", "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
	}
	
	
	
}















