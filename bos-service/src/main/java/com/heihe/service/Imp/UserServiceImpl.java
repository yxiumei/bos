package com.heihe.service.Imp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.UserDao;
import com.heihe.domain.AuthRole;
import com.heihe.domain.User;
import com.heihe.service.UserService;
import com.heihe.utils.MD5Utils;
import com.heihe.utils.PageBean;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	/***
	 * 用户登录
	 */
	public User login(User user) {
		//使用MD5加密密码
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(),password);
	}
	/**
	 * 修改密码
	 */
	public void editPassword(String id, String pwd) {
		// 使用MD5加密
		String pass = MD5Utils.md5(pwd);
		userDao.executeUpdate("user.editPassword",pass,id);
		
		
	}
	/**
	 * 添加用户
	 */
	
	public void save(User user, String[] roleIds) {
		// 对密码进行MD5家吗
		String pwd = MD5Utils.md5(user.getPassword());
		user.setPassword(pwd);
		userDao.save(user);
		if (roleIds != null){
			for (String id : roleIds) {
				// 创建角色对象,此时是托管状态
				AuthRole role = new AuthRole(id);
				// 从user中获得角色集合
				user.getAuthRoles().add(role);
			}
		}
		
	}
	/**
	 * 查询用户
	 */
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}
}
