package com.heihe.web.action;


import com.heihe.domain.User;
import com.heihe.dto.PersonalTaskDto;
import com.heihe.service.PersonalTaskService;
import com.heihe.utils.BOSUtils;
import com.heihe.web.action.base.BaseAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
}
