package com.heihe.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */

public class AuthRole implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private String description;
	private Set functions = new HashSet(0);  // 当前角色对应对个权限
	private Set users = new HashSet(0); // 当前角色对应多用户

	// Constructors

	/** default constructor */
	public AuthRole() {
	}

	/** minimal constructor */
	public AuthRole(String id) {
		this.id = id;
	}

	/** full constructor */
	public AuthRole(String id, String name, String code, String description,
			Set functions, Set users) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.functions = functions;
		this.users = users;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set functions) {
		this.functions = functions;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}