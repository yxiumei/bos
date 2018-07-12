package com.heihe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.domain.AuthRole;
import com.heihe.service.RoleService;
import com.heihe.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<AuthRole> {

	@Autowired
	private RoleService roleService;
	// 使用属性驱动获得所有权限的id
	private String functionIds;
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	/**
	 * 添加角色
	 * @return
	 */
	public String add(){
		roleService.save(model,functionIds);
		return LIST;
	}
	/**
	 * 分页查询角色
	 */
	public String pageQuery(){
		roleService.pageQuery(pageBean);
		this.java2json(pageBean, new String[]{"detachedCriteria","functions","users"});
		return null;
	}
	/**
	 * 查询所有角色，返回ajax数据
	 */
	public String listAjax(){
		List<AuthRole> list = roleService.findAll();
		this.java2json(list, new String[]{"functions","users","description"});
		return null;
	}
}
