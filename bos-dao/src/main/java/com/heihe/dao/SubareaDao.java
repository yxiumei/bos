package com.heihe.dao;

import java.util.List;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.Subarea;

public interface SubareaDao extends IBaseDao<Subarea>{

	// 查询通过省份查询有多少个分区
	public List<Object> findSubareasGrupByProvince();

}
