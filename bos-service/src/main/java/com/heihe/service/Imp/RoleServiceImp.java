package com.heihe.service.Imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.RoleDao;
import com.heihe.domain.AuthRole;
import com.heihe.domain.Function;
import com.heihe.service.RoleService;
import com.heihe.utils.PageBean;
@Service
@Transactional
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleDao roleDao;
	/**
	 * 添加角色
	 */
	public void save(AuthRole model, String functionIds) {
		// 添加角色
		roleDao.saveOrupdate(model);
		if(StringUtils.isNotBlank(functionIds)){
			//为角色添加限权
			String[] strings = functionIds.split(",");
			for (String functionId : strings) {
				// 创建一个限权，设置id，此时是一个游离(托管)状态
				Function  function = new Function();
				function.setId(functionId);
				// 为角色添加限权
				model.getFunctions().add(function);
			}

		}
	}
	/**
	 * 分页查询角色
	 */
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}
	/**
	 * 查询所有角色
	 */
	public List<AuthRole> findAll() {
		return roleDao.finAll();
	}

}
