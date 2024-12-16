package com.kmsjwt.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kmsjwt.dto.UserDTO;
import com.kmsjwt.model.UserEntity;
import com.kmsjwt.repo.UserRepo;
import com.kmsjwt.service.UserService;
import com.kmsjwt.util.ObjectConverter;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private ObjectConverter objectConverter;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JWTService jwtService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public String register(UserDTO userDTO) {
		UserEntity registerenEntity = objectConverter.convertObject(userDTO, UserEntity.class);
		String pas = encoder.encode(registerenEntity.getPassword());
		registerenEntity.setPassword(pas);
		userRepo.save(registerenEntity);
		return "Registration completed";
	}

	@Override
	public UserDTO getUser(Long id) {
		Optional<UserEntity> usr = userRepo.findById(id);
		UserDTO dto = objectConverter.convertObject(usr.get(), UserDTO.class);
		return dto;
	}

	@Override
	public List<UserEntity> getAllUsers() {

		return userRepo.findAll();
	}

	@Override
	public String login(UserDTO userDto) {
		System.out.println("test");
		Authentication auth = manager
				.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));
		if (auth.isAuthenticated()) {
			return jwtService.generateToken(userDto.getUserName());
		}
		return "failed";
	}

}
