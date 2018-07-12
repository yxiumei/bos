package com.heihe.dao;

import java.util.List;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.Region;

public interface RegionDao extends IBaseDao<Region>{
	// 通过模糊查询获得区域全名
	List<Region> getRgionNameByQ(String q);

}
