package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPassword(String userName, String password);
}
