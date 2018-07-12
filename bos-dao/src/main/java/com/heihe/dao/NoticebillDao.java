package com.heihe.dao;

import java.util.List;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.Noticebill;

public interface NoticebillDao extends IBaseDao<Noticebill>{
	// 查询未分配取派员的业务通知单
	public List<Noticebill> findnoassociations();

}
