package com.heihe.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *用户
 */

public class User implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String password;
	private Double salary;  //薪水
	private Date birthday;
	private String gender;
	private String station;
	private String telephone;
	private String remark;
	private Set noticebills = new HashSet(0);
	private Set<AuthRole> authRoles = new HashSet(0);
	
	// 创建get方法用于传输用户所属角色数据
	public String getUserRoles(){
		String roles = "";
		for (AuthRole role : authRoles) {
			roles +=role.getName()+" ";
		}
		return roles;
	}
	// 把日期类型转换成字符串
	public String getBirthdayStr(){
		if (birthday != null){
			String format = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
			return format;
		}else{
			return "暂无数据";
		}
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Set getNoticebills() {
		return noticebills;
	}
	public void setNoticebills(Set noticebills) {
		this.noticebills = noticebills;
	}
	public Set getAuthRoles() {
		return authRoles;
	}
	public void setAuthRoles(Set authRoles) {
		this.authRoles = authRoles;
	}
	public User(String id, String username, String password, Double salary,
			Date birthday, String gender, String station, String telephone,
			String remark, Set noticebills, Set authRoles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.salary = salary;
		this.birthday = birthday;
		this.gender = gender;
		this.station = station;
		this.telephone = telephone;
		this.remark = remark;
		this.noticebills = noticebills;
		this.authRoles = authRoles;
	}
	public User() {
		super();
	}

	

}