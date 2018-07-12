package com.heihe.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.FunctionDao;
import com.heihe.dao.UserDao;
import com.heihe.domain.Function;
import com.heihe.domain.User;

public class BOSRealm extends AuthorizingRealm{
	@Autowired
	private UserDao userDao;
	@Autowired
	private FunctionDao functionDao;
	// 认证
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("realm认证中执行此方法了......");
		// 获得用户名密码令牌
		UsernamePasswordToken mytoken = (UsernamePasswordToken) token;
		// 获得用户名
		String username = mytoken.getUsername();
		// 根据用户名查询用户
		User user = userDao.getUserByUsername(username);
		
		if (user == null){
			// 用户名不存在
			return null;
		}
		// 如果能查到，由此框架令牌中的密码和数据库中对比
		AuthenticationInfo info = new 
				SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// 为用户授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 许可
		//info.addStringPermission("staff_list");
		// 获得当前用户,两种方式
		//User user = (User)principals.getPrimaryPrincipal();
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 判断当前用户是不是admin用户
		List<Function> list = null;
		if ("admin".equals(user.getUsername())){
			// 超级管理员系统内置用户，查询所有权限
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
			list = functionDao.findbyCriteria(detachedCriteria);
		}else{
			// DOTO此处有一个异常还没有解决
			// 通过用户名查询
			list = functionDao.findFunctionListByUserName(user.getId());
		}
		
		// 授权
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}


}
