package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserDAO {
	UserModel findByUserNameAndPassword(String userName, String password);
}
