package com.heihe.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.heihe.domain.User;

public class BOSUtils {

	/**
	 * 获得session
	 * @return
	 */
	public static HttpSession getHttpSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User getLoginUser(){
		return (User) getHttpSession().getAttribute("loginUser");
	}
}
