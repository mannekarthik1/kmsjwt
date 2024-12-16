package com.kmsjwt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kmsjwt.model.UserEntity;
import com.kmsjwt.model.UserPrinciple;
import com.kmsjwt.repo.UserRepo;

@Component
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByUserName(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return new UserPrinciple(userEntity);
	}

}
