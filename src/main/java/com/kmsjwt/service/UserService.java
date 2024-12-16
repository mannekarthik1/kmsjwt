package com.kmsjwt.service;


import java.util.List;

import com.kmsjwt.dto.UserDTO;
import com.kmsjwt.model.UserEntity;

public interface UserService {
	
	String register(UserDTO userDTO);
	UserDTO getUser(Long id);
	List<UserEntity> getAllUsers();
	String login(UserDTO userDto);

}
