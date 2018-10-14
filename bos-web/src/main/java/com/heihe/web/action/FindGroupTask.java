package com.heihe.web.action;

import com.heihe.domain.Staff;
import com.heihe.domain.TransferTask;
import com.heihe.domain.Workbill;
import com.heihe.service.TaskServer;
import com.heihe.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 查询所有任务
 */
@Controller
@Scope("prototype")
public class FindGroupTask extends BaseAction<Workbill>{

    @Autowired
    private TaskServer taskServer;

    public String taskList(){
        DetachedCriteria criteria = pageBean.getDetachedCriteria();
        Staff staff = model.getStaff();
        if (staff !=null){
            String id = staff.getId();
            // 涉及多表连接，需要创建别名
            criteria.createAlias("staff", "s");
            if (StringUtils.isNotBlank(id)){
                criteria.add(Restrictions.like("s.id", id));
            }
        }
        taskServer.pageQuery(pageBean);
        this.java2json(pageBean, new String[] { "currentPage",
                "detachedCriteria", "pageSize"});
        return null;
    }




}
