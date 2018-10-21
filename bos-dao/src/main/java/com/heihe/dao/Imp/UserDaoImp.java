package com.heihe.dao.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heihe.dao.UserDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.User;
/**
 * @author 杨秀眉
 */
@Repository("userDaoImp")
public class UserDaoImp extends IBaseDaoImp<User> implements UserDao{

	/**
	 * 登录验证
	 */
	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = " FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过用户明年查询用户
	 */
	@Override
	public User getUserByUsername(String username) {
		String hql = " FROM User u WHERE u.username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
