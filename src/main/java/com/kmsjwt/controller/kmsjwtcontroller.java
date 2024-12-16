package com.kmsjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kmsjwt.dto.UserDTO;
import com.kmsjwt.model.UserEntity;
import com.kmsjwt.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class kmsjwtcontroller {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public String register(@RequestBody UserDTO userDTO) {
		return userService.register(userDTO);
	}

	@PostMapping("/login")
	public String login(@RequestBody UserDTO userDTO) {
		return userService.login(userDTO);
	}

	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
		return (CsrfToken) httpServletRequest.getAttribute("_csrf");
	}

	@PostMapping("/get/user")
	public UserDTO getuser(@RequestBody UserDTO userDTO) {
		return userService.getUser(userDTO.getId());
	}

	@GetMapping("/getallusers")
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

}
