package com.heihe.service;

import java.util.List;

import com.heihe.domain.Noticebill;

public interface NoticebillService {

	//保存业务通知单
	public void save(Noticebill model);
	// 查询为分配取派员的业务通知单
	public List<Noticebill> findnoassociations();
	// 手动分配取派员
	public void manAllotStaff(Noticebill model);

}
