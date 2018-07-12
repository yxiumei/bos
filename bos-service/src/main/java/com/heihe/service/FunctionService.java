package com.heihe.service;

import java.util.List;

import com.heihe.domain.Function;
import com.heihe.utils.PageBean;

public interface FunctionService {
	// 查询所有父节点功能
	public List<Function> findAll();
	// 添加权限
	public void save(Function model);
	// 分页查询
	public void pageQuery(PageBean pageBean);
	// 查询菜单
	public List<Function> findMenu();
	// 通过json查询权限
	public List<Function> findFunctionByRoleId(String roleId);

}
