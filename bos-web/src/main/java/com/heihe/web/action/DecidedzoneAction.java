package com.heihe.web.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.crm.Customer;
import com.heihe.crm.ICustomerService;
import com.heihe.domain.Decidedzone;
import com.heihe.domain.Staff;
import com.heihe.service.DecidedzoneService;
import com.heihe.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

	// 使用属性驱动接收多个分区的id
	private String[] subareaId;
	@Autowired
	private DecidedzoneService decidedzoneService;
	/**
	 * 添加定区
	 */
	public String add(){
		decidedzoneService.add(model,subareaId);
		return  LIST;
	}
	/**
	 * 分页查询定区
	 */
	public String pageQuery() {
		// 添加离线查询对象
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		String id = model.getId();
		// 动态添加离线查询添加
		if (StringUtils.isNotBlank(id)){
			detachedCriteria2.add(Restrictions.like("id","%"+ id+"%"));
		}
		Staff staff = model.getStaff();
		if (staff !=null){
			String station = staff.getStation();
			// 涉及多表连接，需要创建别名
			detachedCriteria2.createAlias("staff", "s");
			if (StringUtils.isNotBlank(station)){
				detachedCriteria2.add(Restrictions.like("s.station", "%"+station+"%"));
			}
		}
		
		decidedzoneService.pageQuery(pageBean);
		this.java2json(pageBean, new String[] { "currentPage",
				"detachedCriteria", "pageSize", "subareas", "decidedzones" });
		return null;
	}
	
	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
	// 注入crm代理对象
	@Autowired
	private ICustomerService iCustomerService;
	
	/**
	 * 查询未关联定区的客户
	 */
	public String findListNotAssociation(){
		List<Customer> list = iCustomerService.findListNotAssociation();
		this.java2json(list, new String[]{});
		return null;
	}
	
	/**
	 * 远程调用crm,通过定区id，获得关联的客户
	 */
	public String findListAsscoiation(){
		String decidedzoneId = model.getId();
		List<Customer> list2 = iCustomerService.findListHasAssociation(decidedzoneId);
		this.java2json(list2, new String[]{});
		return null;
	}
	
	// 通过属性驱动获得客户的id
	private List<Integer> customerIds;
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	public List<Integer> getCustomerIds() {
		return customerIds;
	}
	/*
	 * 远程调用crm，修改所要关联的定区 
	 */
	public String assigncustomerstodecidedzone(){
		iCustomerService.assigncustomerstodecidedzone(model.getId(), customerIds);
		return LIST;
	}

}
