package com.gdu.halbae.service;

import javax.servlet.http.HttpServletResponse;

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
}















