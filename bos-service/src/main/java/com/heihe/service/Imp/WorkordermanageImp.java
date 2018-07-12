package com.heihe.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.domain.Workordermanage;
import com.heihe.service.WorkorderService;
@Service
@Transactional
public class WorkordermanageImp implements WorkorderService {

	@Autowired
	private WorkordermanageDao workordermanageDao;
	/**
	 * 添加工作单
	 */
	public void save(Workordermanage model) {
		workordermanageDao.save(model);
	}

}
