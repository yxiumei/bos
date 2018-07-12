package com.heihe.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.RegionDao;
import com.heihe.domain.Region;
import com.heihe.service.RegionService;
import com.heihe.utils.PageBean;
@Service
@Transactional
public class RegionServiceImp implements RegionService {
	@Autowired
	private RegionDao regionDao;
	/**
	 * 批量插入区域
	 */
	public void batchImportRegion(List<Region> list) {
		for (Region region : list) {
			regionDao.saveOrupdate(region);
		}
	}
	/**
	 * 使用分页查询
	 */
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
	}
	/**
	 * 查询区域名。用作下拉框选择
	 */
	public List<Region> ajaxGetList() {
		
		return regionDao.finAll();
	}
	/**
	 * 通过迷惑查询
	 */
	public List<Region> queryListByQ(String q) {
		
		return regionDao.getRgionNameByQ(q);
	}
	/**
	 * 编辑区域获得区域名。用作下拉框选择
	 */
	public void updateRegion(Region model) {
		regionDao.saveOrupdate(model);
	}
	/**
	 * 添加区域
	 */
	public void add(Region model) {
		regionDao.save(model);
		
	}
	/**
	 * 批量删除分区
	 */
	public void deleBatch(String ids) {
		String[] split = ids.split(",");
		for (String id : split) {
			regionDao.executeUpdate("delete_Batch", id);
		}
		
	}

}
