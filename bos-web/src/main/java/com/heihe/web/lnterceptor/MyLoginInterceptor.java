package com.heihe.web.lnterceptor;

import com.heihe.domain.User;
import com.heihe.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 *自定义拦截器，判断是否登录
 * @author 杨秀眉
 *
 */

public class MyLoginInterceptor extends MethodFilterInterceptor{

	
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获得session
		User user = BOSUtils.getLoginUser();
		if (user == null){
			// 跳转到登录页面
			return "login";
		}
		// 用户已经登录，放行
		return invocation.invoke();
	}

}
