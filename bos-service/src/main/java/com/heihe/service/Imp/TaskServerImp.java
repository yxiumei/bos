package com.heihe.service.Imp;

import com.heihe.dao.TranferTaskDao;
import com.heihe.dao.WorkbillDao;
import com.heihe.domain.Noticebill;
import com.heihe.domain.Staff;
import com.heihe.domain.Workbill;
import com.heihe.domain.ZzTransferTask;
import com.heihe.dto.TaskDto;
import com.heihe.enums.TaskEnum;
import com.heihe.service.TaskServer;
import com.heihe.utils.PageBean;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 杨秀眉
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TaskServerImp implements TaskServer {

    @Autowired
    private WorkbillDao workbillDao;
    @Autowired
    private TranferTaskDao tranferTaskDao;
    /**
     * 查询任务列表
     * @param pageBean
     */
    @Override
    public PageBean pageQuery(PageBean pageBean) {
        workbillDao.pageQuery(pageBean);
        if (pageBean != null){
            List rows = pageBean.getRows();
            List<TaskDto> list = new ArrayList<>();

            for (Object obj: rows) {
                TaskDto taskDto = new TaskDto();
                Workbill workbill = (Workbill) obj;
                Staff staffs = workbill.getStaff();
                Noticebill noticebill = workbill.getNoticebill();
                taskDto.setArrivecity(noticebill.getArrivecity());
                taskDto.setPickaddress(noticebill.getPickaddress());
                taskDto.setStartCity(noticebill.getStartCity());
                taskDto.setProduct(noticebill.getProduct());
                taskDto.setStatus(workbill.getPickstate());
                taskDto.setSafferName(staffs.getName());
                taskDto.setSafferTelephone(staffs.getTelephone());
                taskDto.setSafferNo(staffs.getId());
                taskDto.setTaskNo(workbill.getId());
                list.add(taskDto);
            }
            pageBean.setRows(list);
        }
        return pageBean;

    }

    @Override
    public void pickUpTask(String wordIds) {
        Workbill workbill = workbillDao.findById(wordIds);
        workbill.setPickstate(TaskEnum.DISPATCHING.getMsg());
        workbillDao.update(workbill);
        ZzTransferTask transferTask = new ZzTransferTask();
        Noticebill noticebill = workbill.getNoticebill();
        if (null != noticebill){
            Staff staff = noticebill.getStaff();
            transferTask.setStartPostion(noticebill.getStartCity());
            transferTask.setMiddiePostion(noticebill.getStartCity());
            transferTask.setEndPostion(noticebill.getArrivecity());
            transferTask.setStatus(TaskEnum.DISPATCHING.getCode());
            transferTask.setWorkId(wordIds);
            transferTask.setStaffId(staff.getId());
            // 拾取时间
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            transferTask.setTaskTime(ts);
            tranferTaskDao.save(transferTask);
        }
    }
}
