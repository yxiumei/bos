package com.heihe.service.Imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.SubareaDao;
import com.heihe.domain.Subarea;
import com.heihe.service.SubareaService;
import com.heihe.utils.PageBean;
@Service
@Transactional
public class SubareaServiceImp implements SubareaService {

	@Autowired
	private SubareaDao subareaDao;
	/**
	 * 保存分区
	 */
	public void save(Subarea model) {
		subareaDao.save(model);
	}
	// 区域查询
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}
	// 查询所有分区
	public List<Subarea> findAll() {
		return subareaDao.finAll();
	}
	/**
	 * 查询没有与定区关联的分区
	 */
	public List<Subarea> findListNotAsscoiation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		// 对联对象decidedzones为空
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findbyCriteria(detachedCriteria);
	}
	/**
	 * 通过定区查询所分区的id
	 */
	public List<Subarea> findListByDecidedzone(String decidedzoneId) {
		// 方法1：可以直接查询定区，然后从定区中获得分区(效率低)
		// 方法2：通过添加离线条件，直接在分区中查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		// 条件条件
		detachedCriteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		return subareaDao.findbyCriteria(detachedCriteria);
	}
	/**
	 * 查询省份中有多少个分区
	 */
	public List<Object> findSubareasGrupByProvince() {
		return subareaDao.findSubareasGrupByProvince();
	}
	/**
	 * 修改定区
	 */
	public void editSubarea(Subarea model) {
		subareaDao.update(model);
	}
	/**
	 * 删除分区
	 */
	public void delBatch(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] split = ids.split(",");
			for (String id : split) {
				subareaDao.executeUpdate("subarea_Batch",id);
			}
		}
	}
	
	

}
