package com.heihe.service;

import com.heihe.domain.User;
import com.heihe.utils.PageBean;

public interface UserService {

	public User login(User user);

	public void editPassword(String id, String pwd);
	// 添加用户
	public void save(User model, String[] roles);
	// 分页查询
	public void pageQuery(PageBean pageBean);

    void delBatch(String ids);
}
