package com.satya.service;

import com.satya.dto.LoginForm;
import com.satya.entity.UserEntity;
import com.satya.util.ServiceMsg;

public interface UserService {
	public ServiceMsg register(UserEntity user);

	public ServiceMsg login(LoginForm loginData);
	
	public boolean checkUser();
	
	public void logout();

}
