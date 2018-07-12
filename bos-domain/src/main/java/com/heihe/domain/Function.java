package com.heihe.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 限权
 */

public class Function implements java.io.Serializable {

	// Fields

	private String id;
	private Function parentFunction; // 上一级权限
	private String name;
	private String code;
	private String description;
	private String page;
	private String generatemenu;  // 是否生成菜单 1:是，0：否
	private Integer zindex;
	private Set authRoles = new HashSet(0);  // 角色
	private Set childFunctions = new HashSet(0); // 下一级权限
	private Set children = new HashSet(0);
	
	private String pId;
	public String getpId() {
		if (parentFunction == null){
			return "0";
		}
		return parentFunction.getId();
	}
	// 用于做json 格式传递数据
	private String text;
	public Set getChildren() {
		return childFunctions;
	}
	
	public String getText() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Function getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public Set getAuthRoles() {
		return authRoles;
	}
	public void setAuthRoles(Set authRoles) {
		this.authRoles = authRoles;
	}
	public Set getChildFunctions() {
		return childFunctions;
	}
	public void setChildFunctions(Set childFunctions) {
		this.childFunctions = childFunctions;
	}
	public Function(String id, Function parentFunction, String name,
			String code, String description, String page, String generatemenu,
			Integer zindex, Set authRoles, Set childFunctions) {
		super();
		this.id = id;
		this.parentFunction = parentFunction;
		this.name = name;
		this.code = code;
		this.description = description;
		this.page = page;
		this.generatemenu = generatemenu;
		this.zindex = zindex;
		this.authRoles = authRoles;
		this.childFunctions = childFunctions;
	}
	public Function() {
		super();
	}

	
}