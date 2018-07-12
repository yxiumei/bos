package com.heihe.service;

import java.util.List;

import com.heihe.domain.Decidedzone;
import com.heihe.utils.PageBean;

public interface DecidedzoneService {
	// 添加定区
	public void add(Decidedzone model, String[] subareaId);
	// 分页查询定区
	public void pageQuery(PageBean pageBean);

}
