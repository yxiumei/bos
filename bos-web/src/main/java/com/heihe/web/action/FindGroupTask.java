package com.heihe.web.action;

import com.heihe.domain.Staff;
import com.heihe.domain.ZzTransferTask;
import com.heihe.domain.User;
import com.heihe.domain.Workbill;
import com.heihe.service.TaskServer;
import com.heihe.utils.BOSUtils;
import com.heihe.web.action.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 查询所有任务
 * @author 杨秀眉
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

    /**
     * 拾取任务
     */
    public String pickUpTask(){
        User user = BOSUtils.getLoginUser();
        if (user.getId().equals(safferNo) || "admin".equals(user.getUsername())){
            taskServer.pickUpTask(wordIds);
        }
        return LIST;
    }

    private String wordIds;
    private String safferNo;

    public String getWordIds() {
        return wordIds;
    }

    public void setWordIds(String wordIds) {
        this.wordIds = wordIds;
    }

    public String getSafferNo() {
        return safferNo;
    }

    public void setSafferNo(String safferNo) {
        this.safferNo = safferNo;
    }
}
