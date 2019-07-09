package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPassword(String userName, String password) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user as u");
		sql.append(" INNER JOIN role as r ON r.id=u.roleid");
		sql.append(" WHERE username=? AND password =?");
		List<UserModel> listUser = query(sql.toString(), new UserMapper(), userName,password);
		return listUser.isEmpty() ? null : listUser.get(0);
	}

	

}
