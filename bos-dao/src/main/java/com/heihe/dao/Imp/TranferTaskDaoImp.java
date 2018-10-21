package com.heihe.dao.Imp;

import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.dao.TranferTaskDao;
import com.heihe.domain.ZzTransferTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 杨秀眉
 */
@Repository("tranferTaskDaoImp")
public class TranferTaskDaoImp extends IBaseDaoImp<ZzTransferTask> implements TranferTaskDao {

    @Override
    public List<ZzTransferTask> getListByStafferId(String staffId) {
       String hql = " FROM ZzTransferTask z WHERE z.staffId = ?";
        List<ZzTransferTask> list = (List<ZzTransferTask>) this.getHibernateTemplate().find(hql, staffId);
        return list;
    }
}
