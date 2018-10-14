package com.heihe.service.Imp;

import com.heihe.dao.WorkbillDao;
import com.heihe.domain.Noticebill;
import com.heihe.domain.Staff;
import com.heihe.domain.Workbill;
import com.heihe.dto.TaskDto;
import com.heihe.service.TaskServer;
import com.heihe.utils.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskServerImp implements TaskServer {

    @Autowired
    private WorkbillDao workbillDao;
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
}
