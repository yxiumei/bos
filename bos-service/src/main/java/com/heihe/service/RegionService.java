package com.heihe.service;

import java.util.List;

import com.heihe.domain.Region;
import com.heihe.utils.PageBean;

public interface RegionService {

	public void batchImportRegion(List<Region> list);
	// 分页查询
	public void pageQuery(PageBean pageBean);
	// 获得区域名字
	public List<Region> ajaxGetList();
	// 模糊查询获得区域名字
	public List<Region> queryListByQ(String q);
	// 编辑区域
	public void updateRegion(Region model);
	// 添加定区
	public void add(Region model);
	// 删除定区
	public void deleBatch(String ids);

}
