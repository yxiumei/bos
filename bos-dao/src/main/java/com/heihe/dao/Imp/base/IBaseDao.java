package com.heihe.dao.Imp.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.heihe.utils.PageBean;

/**
 * 持久层通用接口
 * @author 杨秀眉
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 保存
	 * @param entity
	 */
    public void save(T entity);
    /**
     * 通过对象删除
     * @param entity
     */
	public void delete(T entity);
	/**
	 * 通过对象修改
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> finAll();
	/**
	 * 修改
	 */
	public void executeUpdate(String quertName, Object... objects);
	// 分页查询方法
	public void pageQuery(PageBean pageBean);
	public void saveOrupdate(T entity);
	/**
	 * 通过离线对象查询
	 */
	public List<T> findbyCriteria(DetachedCriteria detachedCriteria);
}
