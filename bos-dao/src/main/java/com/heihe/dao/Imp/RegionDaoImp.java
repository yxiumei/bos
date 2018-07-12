package com.heihe.dao.Imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.heihe.dao.RegionDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.Region;

@Repository
public class RegionDaoImp extends IBaseDaoImp<Region> implements RegionDao {

	/**
	 * 使用Q进行模糊查需用于下拉框选择
	 */
	public List<Region> getRgionNameByQ(String q) {
		String hql = "FROM Region r WHERE r.shortcode LIKE ?"
				+ " OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%");
		return list;
	}
}
