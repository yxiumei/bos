package com.heihe.dao.Imp;

import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.dao.TranferTaskDao;
import com.heihe.domain.ZzTransferTask;
import org.springframework.stereotype.Repository;

/**
 * @author 杨秀眉
 */
@Repository("tranferTaskDaoImp")
public class TranferTaskDaoImp extends IBaseDaoImp<ZzTransferTask> implements TranferTaskDao {
}
