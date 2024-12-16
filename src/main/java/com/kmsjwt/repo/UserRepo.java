package com.kmsjwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmsjwt.model.UserEntity;

@Repository
public interface UserRepo  extends JpaRepository<UserEntity, Long>{
	UserEntity findByUserName(String userName);

}
