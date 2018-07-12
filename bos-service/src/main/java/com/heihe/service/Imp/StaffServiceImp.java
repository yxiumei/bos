package com.heihe.service.Imp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.dao.StaffDao;
import com.heihe.domain.Staff;
import com.heihe.service.StaffService;
import com.heihe.utils.PageBean;
@Service
@Transactional
public class StaffServiceImp implements StaffService {
	
    @Autowired
	private StaffDao staffDao;
	/**
	 * 添加取派员
	 */
	public void addSatff(Staff model) {
		staffDao.save(model);
	}
	/**
	 * 分页查询
	 */
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
		
	}
	/**
	 * 批量删除取派员
	 */
	public void delBatch(String ids) {
		// 把传过来的ids字符串进行拆分
		String[] id = ids.split(",");
		if (StringUtils.isNoneBlank(id)){
			for (String staffId: id){
				staffDao.executeUpdate("staff.delBatch", staffId);
			}
		}
		
	}
	/**
	 * 批量恢复数据
	 */
	public void recoverBatch(String reids) {
		// 把传过来的额id进行拆分
		String[] id = reids.split(",");
		if (StringUtils.isNoneBlank(id)){
			for (String staffId : id) {
				staffDao.executeUpdate("staff.recoverBatch", id);
			}
		}
	}
	/**
	 * 通过id查询取派员
	 */
	public Staff findStaffById(String id) {
	
		return staffDao.findById(id);
	}
	/**
	 * 编辑取派员
	 */
	public void editStaff(Staff staff) {
		staffDao.update(staff);
	}
	/**
	 * 查询未删除的取派员
	 */
	public List<Staff> findListNoDelete() {
       // 创建离线查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		criteria.add(Restrictions.eq("deltag", "0")); // 表示未被删除
		return staffDao.findbyCriteria(criteria);
	}
	/**
	 * 查询所有取派员
	 */
	public List<Staff> findAllStaff() {
		return staffDao.finAll();
	}

}
