package com.heihe.service.Imp;

import com.heihe.dao.TranferTaskDao;
import com.heihe.dao.WorkbillDao;
import com.heihe.domain.Noticebill;
import com.heihe.domain.Workbill;
import com.heihe.domain.ZzTransferTask;
import com.heihe.dto.PersonalTaskDto;
import com.heihe.enums.TaskEnum;
import com.heihe.service.PersonalTaskService;
import com.heihe.utils.PageBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.AbstractProcessTask;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 个人任务组
 * @author 杨秀眉
 */
@Service
@Transactional
public class PersonalTaskServiceImpl implements PersonalTaskService {

    @Autowired
    private TranferTaskDao tranferTaskDao;
    @Autowired
    private WorkbillDao workbillDao;

    @Override
    public List<PersonalTaskDto> getList(String stafferId) {
        if (StringUtils.isEmpty(stafferId)){
            return null;
        }
        List<ZzTransferTask> listByStafferId = tranferTaskDao.getListByStafferId(stafferId);
        List<String> workIdList = listByStafferId.stream().map(ZzTransferTask::getWorkId).collect(Collectors.toList());
        List<Workbill> workbills = new ArrayList<>();
        for (String workId: workIdList) {
            Workbill byId = workbillDao.findById(workId);
            workbills.add(byId);
        }
        List<Noticebill> collectList = workbills.stream().map(Workbill::getNoticebill).collect(Collectors.toList());
        List<PersonalTaskDto> list = getTask(listByStafferId, collectList);
        return list;
    }

    @Override
    public List<PersonalTaskDto> getAllList() {
        List<ZzTransferTask> list = tranferTaskDao.finAll();
        List<String> collect = list.stream().map(ZzTransferTask::getWorkId).collect(Collectors.toList());
        List<Workbill> workbills = new ArrayList<>();
        for (String workId : collect){
            Workbill byId = workbillDao.findById(workId);
            workbills.add(byId);
        }
        List<Noticebill> collectList = workbills.stream().map(Workbill::getNoticebill).collect(Collectors.toList());
        List<PersonalTaskDto> list1 = getTask(list, collectList);
        return list1;
    }

    /**
     * 参数封装
     */
    private List<PersonalTaskDto> getTask(List<ZzTransferTask> listByStafferId, List<Noticebill> collectList) {
        List<PersonalTaskDto> list = new ArrayList<>();
        for (int i = 0; i < listByStafferId.size(); i++){
            PersonalTaskDto personalTaskDto = new PersonalTaskDto();
            ZzTransferTask zzTransferTask = listByStafferId.get(i);
            Noticebill noticebill = collectList.get(i);
            BeanUtils.copyProperties(zzTransferTask, personalTaskDto);
            personalTaskDto.setTaskId(zzTransferTask.getId());
            BeanUtils.copyProperties(noticebill, personalTaskDto);
            personalTaskDto.setProductName(noticebill.getProduct());
            personalTaskDto.setReceiverAddress(noticebill.getPickaddress());
            personalTaskDto.setReceiver(noticebill.getDelegater());
            personalTaskDto.setStaffName(noticebill.getStaff().getName());
            list.add(personalTaskDto);
        }
        return list;
    }

    @Override
    public void updatetransferTask(PersonalTaskDto model) {
        String taskId = model.getTaskId();

        ZzTransferTask transferTask = tranferTaskDao.findById(taskId);
        String middiePostion = transferTask.getMiddiePostion();
        // 判断是否到达目标城市
        if (transferTask.getEndPostion().equals(model.getMiddiePostion())) {
            transferTask.setStatus(TaskEnum.HAVE_SIGNED_IN.getCode());
            transferTask.setMiddiePostion(middiePostion + "=>" + model.getMiddiePostion());
        } else {
            transferTask.setMiddiePostion(middiePostion + "=>" + model.getMiddiePostion());
        }
        // 更新任务
        tranferTaskDao.update(transferTask);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        tranferTaskDao.pageQuery(pageBean);
        if  (null != pageBean) {
            List rows = pageBean.getRows();
            List<Workbill> workbills = new ArrayList<>();
            List<ZzTransferTask> list = new ArrayList<>();
            if (!CollectionUtils.isEmpty(rows)) {
                for (Object obj: rows) {
                    list.add((ZzTransferTask) obj);
                }
                List<String> workIdList = list.stream()
                        .map(ZzTransferTask::getWorkId)
                        .collect(Collectors.toList());
                for (String workId: workIdList) {
                    Workbill byId = workbillDao.findById(workId);
                    workbills.add(byId);
                }
                List<Noticebill> collectList = workbills.stream().map(Workbill::getNoticebill).collect(Collectors.toList());
                List<PersonalTaskDto> personalTaskDtos = getTask(list, collectList);
                pageBean.setRows(personalTaskDtos);
            }
        }
    }

}
