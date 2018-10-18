package com.heihe.dao.Imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.heihe.dao.SubareaDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.Subarea;
/**
 * @author 杨秀眉
 */
@Repository
public class SubareaDaoImp extends IBaseDaoImp<Subarea> implements SubareaDao {

	/**
	 * 查询通过省份查询有多少个分区
	 */
	@Override
	public List<Object> findSubareasGrupByProvince() {
		String hql = "SELECT r.province,COUNT(*) FROM Subarea s LEFT OUTER JOIN s.region r Group BY"
				+ " r.province";
		List<Object> find = (List<Object>) this.getHibernateTemplate().find(hql);
		return find;
	}

	
}
