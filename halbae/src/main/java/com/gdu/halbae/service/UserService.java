package com.gdu.halbae.service;

import javax.servlet.http.HttpServletResponse;

import com.gdu.halbae.domain.UserDTO;

public interface UserService {
	public String checkUniqueId(UserDTO userDTO);
	public int insertUser(UserDTO userDTO);
}
