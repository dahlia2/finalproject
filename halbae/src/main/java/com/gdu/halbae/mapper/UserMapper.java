package com.gdu.halbae.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.halbae.domain.UserDTO;

@Mapper
public interface UserMapper {
	// 회원가입
	public UserDTO checkUniqueId(String userId);
	public int insertUser(UserDTO userDTO);
	//로그인
	public UserDTO selectLoginInfo(Map<String, Object> map);
	public int insertAutoLogin(UserDTO userDTO);
	public int deleteAutoLogin(String userId);
}
