package com.heihe.dao.Imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.heihe.dao.FunctionDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.Function;
import com.heihe.utils.PageBean;
@Repository
public class FunctionDaoImp extends IBaseDaoImp<Function> implements FunctionDao {

	// 查询父节点，用于Ztree显示
	public List<Function> finAll() {
		String sql = "FROM Function f WHERE f.parentFunction IS NULL";
		return (List<Function>) this.getHibernateTemplate().find(sql);
	}

	/**
	 * 通过用户查询权限
	 */
	public List<Function> findFunctionListByUserName(String userId) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.authRoles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ?";
		List<Function> find = (List<Function>) this.getHibernateTemplate().find(hql, userId);
		return find;
	}

	/**
	 * 查询所有菜单
	 */
	public List<Function> finAllMenu() {
		String sql = "FROM Function f WHERE f.generatemenu ='1' ORDER BY f.zindex ASC";
		return (List<Function>) this.getHibernateTemplate().find(sql);
	}

	/**
	 * 通过用户id查询权限用于显示菜单
	 */
	public List<Function> findMenuByUserId(String userId) {
		String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.authRoles"
				+ " r LEFT OUTER JOIN r.users u WHERE u.id = ? and f.generatemenu ='1'"
				+ " ORDER BY f.zindex ASC";
		List<Function> find = (List<Function>) this.getHibernateTemplate().find(hql, userId);
		return find;
	}

	/**
	 * 通过角色id查询权限
	 */
	public List<Function> findFunctionByRoleId(String roleId) {
		/**
		 * select f.name,rf.role_id from auth_function f  JOIN role_function rf on
		 *  rf.function_id =f.idjoin auth_role r on r.id = rf.role_id  and
		 *   rf.role_id='297e30a162f8334a0162f834e6040000'
		 */
		String hql = "SELECT f FROM Function f JOIN f.authRoles r WHERE r.id = ?";
		List<Function> function = (List<Function>) this.getHibernateTemplate().find(hql, roleId);
		return function;
	}

}
