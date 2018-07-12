package com.heihe.dao.Imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.heihe.dao.WorkbillDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.Workbill;
import com.heihe.utils.PageBean;
@Repository
public class WorkbillDaoImp extends IBaseDaoImp<Workbill> implements WorkbillDao {

	/**
	 * 查询新单
	 */
	public List<Workbill> findNewWorkbills() {
		return null;
	}

}
