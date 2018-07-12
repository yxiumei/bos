package com.heihe.dao.Imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.heihe.dao.NoticebillDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.Noticebill;
import com.heihe.utils.PageBean;
@Repository
public class NoticebillDaoImp extends IBaseDaoImp<Noticebill> implements NoticebillDao {

	/**
	 * 查询分配取派的业务通知单
	 */
	public List<Noticebill> findnoassociations() {
		String hql = " FROM Noticebill WHERE staff IS NULL";
		List<Noticebill> find = (List<Noticebill>) this.getHibernateTemplate().find(hql);
		return find;
	}

	

}
