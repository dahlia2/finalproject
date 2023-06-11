package com.gdu.halbae.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.halbae.domain.UserDTO;

@Mapper
public interface UserMapper {
	// 회원가입
	public UserDTO checkUniqueId(String userId);
	public int checkIdCountByTel(String userId);
	public int insertUser(UserDTO userDTO);
	//로그인
	public UserDTO selectLoginInfo(Map<String, Object> map);
	public int insertAutoLogin(UserDTO userDTO);
	public int deleteAutoLogin(String userId);
	// 자동로그인
	public UserDTO selectUserDTOByAutoLoginId(String userAutoLoginId);
	// 전화번호로 아이디 찾기
	public List<UserDTO> selectUserByTel(String userTel);
	// 임시 비번으로 바꾸기
	public int updateTempPw(UserDTO userDTO);
	
	// 프로필 정보 가져오기
	public UserDTO selectUserProfile(String loginId);
	// 유저 이름 변경하기
	public int updateUserName(UserDTO userDTO);
}
