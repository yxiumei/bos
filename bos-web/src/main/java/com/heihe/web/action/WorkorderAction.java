package com.heihe.web.action;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.domain.Workordermanage;
import com.heihe.service.WorkorderService;
import com.heihe.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class WorkorderAction extends BaseAction<Workordermanage> {
	@Autowired
	private WorkorderService workorderService; 
	public String add() throws IOException{
		String falg = "1";
		try {
			workorderService.save(model);
		} catch (Exception e) {
			//在添加时，可能存在主键相同时，添加失败出现异常
			falg = "0";
		}
	    ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
	    ServletActionContext.getResponse().getWriter().print(falg);
		
		return null;
	}
}
