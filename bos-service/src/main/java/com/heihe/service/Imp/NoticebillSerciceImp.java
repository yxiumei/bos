package com.heihe.service.Imp;

import java.sql.Timestamp;
import java.util.List;

import com.heihe.domain.*;
import com.heihe.enums.TaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heihe.crm.ICustomerService;
import com.heihe.dao.DecdezoneDao;
import com.heihe.dao.NoticebillDao;
import com.heihe.dao.StaffDao;
import com.heihe.dao.WorkbillDao;
import com.heihe.service.NoticebillService;
import com.heihe.utils.BOSUtils;
@Service
@Transactional
public class NoticebillSerciceImp implements NoticebillService {
	@Autowired
	private NoticebillDao NoticebillDao;
	@Autowired
	private ICustomerService iCustomerService;
	@Autowired
	private DecdezoneDao decdezoneDao;
	@Autowired
	private WorkbillDao workbillDao;
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private WorkordermanageDao workordermanageDao;
	/**
	 * 添加一个通知单，并尝试自动分单
	 */
	@Override
	public void save(Noticebill model) {
		// 获得当前用户
		User user = BOSUtils.getLoginUser();
		// 保存业务单
		NoticebillDao.save(model);
		// 添加系统操作员
		model.setUser(user);
		// 获取取件地址
		String address = model.getPickaddress();
		// 远程调用crm，通过地址查询定区的id
		String decidedzoneId = iCustomerService.findDecidedzoneIdByAddress(address);
		if (decidedzoneId != null){
			// 通过定区id查询定区，尝试自动分单
			Decidedzone decidedzone = decdezoneDao.findById(decidedzoneId);
			// 通过定区，获得取派员
			Staff staff = decidedzone.getStaff();
			// 为工作业务单分配取派员
			model.setStaff(staff);
			// 设置为自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			// 为取派员生成一个工单
			Workbill workbill = new Workbill();
			// 绑定取派员
			workbill.setStaff(staff);
			// 工单关联页面的通知单
			workbill.setNoticebill(model);
			workbill.setPickstate(TaskEnum.TASK_UNRECEIVED.getMsg());
			workbill.setRemark(model.getRemark());
			// 追单次数
			workbill.setAttachbilltimes(0);
			// 新单
			workbill.setType(Workbill.TYPE_1);
			// 创建时间，当前系统时间
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			// 添加工单
			workbillDao.save(workbill);
			//调用短信平台发送短信
		}else{
			// 没有查询到定区id，不能进行自动分单，需进行手动分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
	}
	/**
	 * 查询未分配取派员的业务通知单
	 */
	@Override
	public List<Noticebill> findnoassociations() {
		
		return NoticebillDao.findnoassociations();
	}
	/**
	 * 手动分派取派员
	 */
	@Override
	public void manAllotStaff(Noticebill model) {
		// 通过取派员id查询取派员
		String staffId = model.getStaff().getId();
		Staff staff = staffDao.findById(staffId);
		// 通过id查询工作单
		model = NoticebillDao.findById(model.getId());
		// 为工作单分配器取派员
		model.setStaff(staff);
		// 为取派员生成一个工单 
		Workbill workbill = new Workbill();
		// 为工单不指定取派员
		workbill.setStaff(staff);
		workbill.setNoticebill(model); // 添加业务通知单
		workbill.setType(Workbill.TYPE_1);
		workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
		workbill.setAttachbilltimes(0);
		workbill.setRemark(model.getRemark());
		workbill.setPickstate(TaskEnum.TASK_UNRECEIVED.getMsg());
		// 添加工单
		workbillDao.save(workbill);
		// 调用短信平台发短信给取派员
		
	}
	

}
