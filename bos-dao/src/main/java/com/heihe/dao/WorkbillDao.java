package com.heihe.dao;

import java.util.List;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.Workbill;

public interface WorkbillDao extends IBaseDao<Workbill>{
	// 查询新单
	public List<Workbill> findNewWorkbills();

}
