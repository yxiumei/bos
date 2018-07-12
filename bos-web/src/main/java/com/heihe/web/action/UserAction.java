package com.heihe.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.crm.Customer;
import com.heihe.crm.ICustomerService;
import com.heihe.domain.User;
import com.heihe.service.UserService;
import com.heihe.utils.BOSUtils;
import com.heihe.utils.MD5Utils;
import com.heihe.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	//属性驱动，接收页面输入的验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	@Autowired
	private UserService userService;
	//使用属性驱动获得选择角色的id
	private String[] roles;
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	/**
	 * 添加用户
	 */
	public String add(){
		userService.save(model,roles);
		return LIST;
	}
	
	/*
	 * 查询用户
	 */
	public String pageQuery(){
		userService.pageQuery(pageBean);
		this.java2json(pageBean, new String[]{"noticebills","authRoles","birthday"});
		return null;
	}

	/**
	 * 用户登录,通过shiro框架认证
	 * @param token 
	 */
	public String login(){
		//从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
			// 使用shrio框架认证,
			Subject subject = SecurityUtils.getSubject(); // 获得前登录用户对象,现在状态为“未认证”
			// 创建用户名，密码立牌
			AuthenticationToken token = new 
					UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));
			try {
				subject.login(token);
				User user = (User) subject.getPrincipal();
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			} catch (Exception e) {
				// 登录失敗
				this.addActionError("用户名或密码错误！");
				return LOGIN;
			}
			
		}else{
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
		return "home";
	}
	
	/**
	 * 用户登录
	 */
	public String login_a(){
		//从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
			//输入的验证码正确
			User user = userService.login(model);
			if(user != null){
				//登录成功,将user对象放入session，跳转到首页
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}else{
				//登录失败，,设置提示信息，跳转到登录页面
				//输入的验证码错误,设置提示信息，跳转到登录页面
				this.addActionError("用户名或者密码输入错误！");
				return LOGIN;
			}
		}else{
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	// 用户注登录
	public String exit() throws Exception {
		// 获得session
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
	
	// 修改密码
	public String editPassword() throws IOException{
		String flag = "1";
		// 获得当前用户
		User user = BOSUtils.getLoginUser();
		// 获得要修改的密码
        String pwd = model.getPassword();
        try {
        	userService.editPassword(user.getId(),pwd);
		} catch (Exception e) {
			flag = "0";
		}
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(flag);
		return null;
	}

}
