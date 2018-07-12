package com.heihe.service;

import java.util.List;

import com.heihe.domain.Staff;
import com.heihe.utils.PageBean;

/**
 * 取派员业务层
 * @author 杨秀眉
 *
 */
public interface StaffService {

	public void addSatff(Staff model);

	public void pageQuery(PageBean pageBean);

	public void delBatch(String ids);

	public Staff findStaffById(String id);

	public void editStaff(Staff staff);

	public List<Staff> findListNoDelete();

	public void recoverBatch(String reids);
	// 查询所有取派员
	public List<Staff> findAllStaff();

}
