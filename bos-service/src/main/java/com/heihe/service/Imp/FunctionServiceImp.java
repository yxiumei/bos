package com.heihe.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.FunctionDao;
import com.heihe.domain.Function;
import com.heihe.domain.User;
import com.heihe.service.FunctionService;
import com.heihe.utils.BOSUtils;
import com.heihe.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImp implements FunctionService{

	@Autowired
	private FunctionDao functionDao;
	/**
	 * 查询父节点功能
	 */
	public List<Function> findAll() {
		
		return functionDao.finAll();
	}
	/**
	 * 添加权限
	 */
	public void save(Function model) {
		functionDao.save(model);
		
	}
	// 分页查询
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
	}
	/**
	 * 查询菜单条显示
	 */
	public List<Function> findMenu() {
		// 获得当前用户，用于判断是否是超级用户
		User user = BOSUtils.getLoginUser();
		List<Function> list = null;
		if (user.getUsername().equals("admin")){
			list = functionDao.finAllMenu(); // 超级用户查询所有菜单
		}else{
			list = functionDao.findMenuByUserId(user.getId());
		}
		return list;
	}
	/**
	 * 通过角色查询权限
	 */
	public List<Function> findFunctionByRoleId(String  roleId) {
		List<Function> list = functionDao.findFunctionByRoleId(roleId);
		return list;
	}

}
