package com.heihe.dao.Imp.base.Imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Source;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.utils.PageBean;

public class IBaseDaoImp<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> entityClass;
	
	
	
	// 根据类型注入spring工厂中的sessionFactory工厂对象
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	// 在父类构造方法中获得entityClass对象
	public IBaseDaoImp() {
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获得父类上声明的泛型数组
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}

	// 保存
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	
	public void update(T entity) {
		
		this.getHibernateTemplate().update(entity);
	}

	
	public T findById(Serializable id) {
		
		return this.getHibernateTemplate().get(entityClass, id);
	}

	
	public List<T> finAll() {
		String hql = "FROM "+entityClass.getSimpleName();
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	
	public void executeUpdate(String quertName, Object... objects) {
		// 获得query对象
		Session currentSession = this.getSessionFactory().getCurrentSession();
		Query query = currentSession.getNamedQuery(quertName);
		// HQL参数赋值
		int i = 0;
		for (Object o:objects){
			query.setParameter(i++,o );
		}
		
		//执行更新
		query.executeUpdate();
	}

	/**
	 * 分页查询
	 */
	public void pageQuery(PageBean pageBean) {
		// 获得当前页，页面显示数据大小
		Integer currentPage = pageBean.getCurrentPage();
		// 当前页为空时
		if (currentPage == null){
			currentPage = 1;
		}
		Integer pageSize = pageBean.getPageSize();
		
		// 获得离线查询对象
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		// 指定发出SQL的形式
		detachedCriteria.setProjection(Projections.rowCount());  // select count(*) from bc_staff
		// 执行查询
		List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long total = countList.get(0);
		pageBean.setTotal(total.intValue());
		
		// 指定离线查询对象发出SQL的形式 select * from bc_staff
		detachedCriteria.setProjection(null);
		// 指定hibernate封装对象的方式
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		
		// 查询显示数据
		int firstResult = (currentPage - 1)*pageSize;
		int maxResults = pageSize;
		List list = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(list);
	}

	/**
	 * 自动判断保存还是修改
	 */
	public void saveOrupdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
		
	}

	/**
	 * 通过离线条件查询
	 */
	public List<T> findbyCriteria(DetachedCriteria detachedCriteria) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
