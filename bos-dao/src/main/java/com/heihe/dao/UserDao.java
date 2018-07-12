package com.heihe.dao;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.User;

public interface UserDao extends IBaseDao<User>{

	public User findUserByUsernameAndPassword(String username, String password);
	// 通过用户名查询用户
	public User getUserByUsername(String username);
	
}
