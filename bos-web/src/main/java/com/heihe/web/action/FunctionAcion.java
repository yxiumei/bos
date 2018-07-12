package com.heihe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.domain.Function;
import com.heihe.service.FunctionService;
import com.heihe.utils.PageBean;
import com.heihe.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class FunctionAcion extends BaseAction<Function>{

	@Autowired
	private FunctionService functionService;
	// 使用属性驱动获得角色的id
	private String roleId;
	/**
	 * 查询父功能点，用于ajax回显
	 * @return
	 */
	public String listAjax(){
		
		List<Function> list = functionService.findAll();
		this.java2json(list, new String[]{"parentFunction","authRoles","childFunctions"});
		return null;
	}
	/**
	 * 查询菜单条
	 */
	public String findMenu(){
		List<Function> list = functionService.findMenu();
		this.java2json(list, new String[]{"parentFunction","authRoles","childFunctions","children"});
		return null;
	}
	/**
	 * 添加权限
	 */
	public String add(){
		// 没有选择选择父功能点时，此时接收到的是一个" "串，而不是null,插入数据会报错
		// 获得父类功能点
		Function parentFunction = model.getParentFunction();
		if (parentFunction !=null && parentFunction.getId().equals("")){
			model.setParentFunction(null);
		}
		functionService.save(model);
		return LIST;
	}
	/**
	 * 分页查询
	 */
	public String pageQuery(){
		functionService.pageQuery(pageBean);
		this.java2json(pageBean, new String[]{"parentFunction","authRoles","childFunctions","detachedCriteria","children"});
		return null;
	}
	/**
	 * 通过角色id查询权限
	 */
	public String findFunctionByRoleId(){
		List<Function> list = functionService.findFunctionByRoleId(roleId);
		this.java2json(list, new String[]{"parentFunction","authRoles","childFunctions","children","pId"});
		return null;
	}
	
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
	
}
