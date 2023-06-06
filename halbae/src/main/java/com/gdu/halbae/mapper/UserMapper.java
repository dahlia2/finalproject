package com.gdu.halbae.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.halbae.domain.UserDTO;

@Mapper
public interface UserMapper {
	public UserDTO checkUniqueId(String userId);
	public int insertUser(UserDTO userDTO);
}
