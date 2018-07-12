package com.heihe.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.DecdezoneDao;
import com.heihe.dao.SubareaDao;
import com.heihe.domain.Decidedzone;
import com.heihe.domain.Subarea;
import com.heihe.service.DecidedzoneService;
import com.heihe.utils.PageBean;
@Service
@Transactional
public class DecdezoneServiceImp implements DecidedzoneService {

	@Autowired
	private DecdezoneDao decdezoneDao;
	@Autowired
	private SubareaDao subareaDao;
	/**
	 * 添加定区
	 */
	public void add(Decidedzone model, String[] subareaId) {
		// 执行这一步不会打印SQL语句，因为主键生成策略是(assigned)手动委派，事务提交时才会发送sql
		decdezoneDao.save(model);
		
		for (String id : subareaId) {
			// 多的一方对外建进行管理
			// 通过id查询出分区
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(model);
		}
	}
	/**
	 * 分页查询定区
	 */
	public void pageQuery(PageBean pageBean) {
		decdezoneDao.pageQuery(pageBean);;
	}

}
