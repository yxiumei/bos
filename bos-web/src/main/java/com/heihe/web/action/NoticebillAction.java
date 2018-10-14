package com.heihe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.crm.Customer;
import com.heihe.crm.ICustomerService;
import com.heihe.domain.Noticebill;
import com.heihe.service.NoticebillService;
import com.heihe.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

	// 注入crm客户端代理对象
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private NoticebillService noticebillService;
	/**
	 * 远程调用crm，通过客户电话查询客户，用于回显
	 */
	public String findCustomerByTelephone(){

		Customer customer = iCustomerService.findCustomerByTelephone(model.getTelephone());
		this.java2json(customer, new String[]{});
		return null;
	}
	// 查询未分配取派员的业务通知单(自动分配失败，手动分配)
	public String findnoassociations(){
		List<Noticebill> list = noticebillService.findnoassociations();
		this.java2json(list, new String[]{"workbills","user","decidedzones"});
		return null;
	}
	
	// 手动分区取派员
	public String manAllotStaff(){
		noticebillService.manAllotStaff(model);
		return LIST;
	}
	
	/**
	 * 添加一个业务通知单，并尝试自动分单
	 */
	public String add(){
		noticebillService.save(model);
		return null;
	}
	
}
