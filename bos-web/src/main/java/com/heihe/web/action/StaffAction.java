package com.heihe.web.action;


import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.heihe.domain.Staff;
import com.heihe.service.StaffService;
import com.heihe.utils.PageBean;
import com.heihe.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	@Autowired
	private StaffService staffService;
	// 添加取派员
	public String add(){
		staffService.addSatff(model);
		return LIST;
	}
	
	/**
	 * 获得离线查询列表
	 * @return
	 */
	public String staffList() throws IOException{
		//添加离线查询对象
		DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
		// 动态添加离线查询条件
		String name = model.getName();
		String telephone = model.getTelephone();
		String station = model.getStation();
		if (StringUtils.isNotBlank(name)){
			detachedCriteria2.add(Restrictions.like("name", "%"+name+"%"));
		}
		if (StringUtils.isNotBlank(telephone)){
			detachedCriteria2.add(Restrictions.like("telephone", "%"+telephone+"%"));
		}
		if (StringUtils.isNotBlank(station)){
			detachedCriteria2.add(Restrictions.like("station", "%"+station+"%"));
		}
		staffService.pageQuery(pageBean);
		// 调用封装方法将Java对象转成字符串
		java2json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
		return null;
	}
	
	// 使用属性驱动获得删除取派员的id
	private String ids;
	// 恢复取派员的id
	private String reids;
	/**
	 * 批量删除取派员
	 */
	//@RequiresPermissions("staff_delete") 执行此方法与需要当前用户有staff_delete限权
	public String delBatch(){
		staffService.delBatch(ids);
		return LIST;
	}
	// 批量恢复取派员
	public String recoverBatch(){
		staffService.recoverBatch(reids);
		return LIST;
	}
	/**
	 * 编辑取派员
	 */
	public String editStaff(){
		// 通过id查询出所要编辑的取派员
		Staff staff = staffService.findStaffById(model.getId());
		// 用编辑的参数对查询出来的进行覆盖
		staff.setName(model.getName());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staffService.editStaff(staff);
		return LIST;
	}
	/**
	 * 查询所有未删除的取派员
	 */
	public String listajax(){
		List<Staff> list = staffService.findListNoDelete();
		this.java2json(list, new String[]{"telephone","haspda","deltag","station","standard","decidedzones"});
		return null;
	}
	
	/**
	 * 查询所有取派员,用作人工调度中下拉框选择
	 * @return
	 */
	public String ajaxlist(){
		List<Staff> list = staffService.findAllStaff();
		this.java2json(list, new String[]{"decidedzones"});
		return null;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setReids(String reids) {
		this.reids = reids;
	}
	
	
}
