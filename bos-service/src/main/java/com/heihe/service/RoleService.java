package com.heihe.service;

import java.util.List;

import com.heihe.domain.AuthRole;
import com.heihe.utils.PageBean;

public interface RoleService {
    // 添加角色
	public void save(AuthRole model, String functionIds);
	// 分页查询角色
	public void pageQuery(PageBean pageBean);
	// 查询所有角色
	public List<AuthRole> findAll();

}
