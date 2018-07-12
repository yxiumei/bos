package com.heihe.dao;

import java.util.List;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.Function;

public interface FunctionDao extends IBaseDao<Function>{
	// 通过用户查询权限
	public List<Function> findFunctionListByUserName(String userId);
	//查询所有菜单条
	public List<Function> finAllMenu();
	// 通过用户id查询对应的菜单条
	public List<Function> findMenuByUserId(String userId);
	// 通过角色id查询权限
	public List<Function> findFunctionByRoleId(String roleId);

}
