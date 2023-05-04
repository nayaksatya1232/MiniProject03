package com.satya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByEmail(String email);
}
