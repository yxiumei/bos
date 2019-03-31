package com.heihe.web.action;


import com.heihe.domain.User;
import com.heihe.dto.PersonalTaskDto;
import com.heihe.service.PersonalTaskService;
import com.heihe.utils.BOSUtils;
import com.heihe.web.action.base.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 个人任务组
 * @author 杨秀眉
 */
@Controller
@Scope("prototype")
public class PersonalTaskAction extends BaseAction<PersonalTaskDto>{

    @Autowired
    private PersonalTaskService personalTaskService;

    /**
     * 获取个人任务列表
     * @return
     */
    public String personalList(){
        User user = BOSUtils.getLoginUser();
        List<PersonalTaskDto> allList;
        if ("admin".equals(user.getUsername())){
            allList = personalTaskService.getAllList();
        } else {
            allList = personalTaskService.getList(user.getId());
        }
        this.java2json(allList, null);
        return null;
    }

    /**
     * 中转个人任务
     */
    public  String  transferTask() throws UnsupportedEncodingException {
        PersonalTaskDto model = new PersonalTaskDto();
        model.setTaskId(id);
        String middiePostion = new String(middiePostions.getBytes("iso-8859-1"),"utf-8");
        model.setMiddiePostion(middiePostion);
        personalTaskService.updatetransferTask(model);
        return LIST;
    }

    private String id;
    private String middiePostions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMiddiePostions() {
        return middiePostions;
    }

    public void setMiddiePostions(String middiePostions) {
        this.middiePostions = middiePostions;
    }
}
