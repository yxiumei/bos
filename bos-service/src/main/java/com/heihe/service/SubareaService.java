package com.heihe.service;

import java.util.List;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.Subarea;
import com.heihe.utils.PageBean;

public interface SubareaService {

	// 添加分区
	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findListNotAsscoiation();

	public List<Subarea> findListByDecidedzone(String decidedzoneId);
	// 查询分区数据，用户区域分布图
	public List<Object> findSubareasGrupByProvince();
	// 修改分区
	public void editSubarea(Subarea model);
	// 删除分区
	public void delBatch(String ids);

}
