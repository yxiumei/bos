package com.heihe.dao;

import com.heihe.dao.Imp.base.IBaseDao;
import com.heihe.domain.ZzTransferTask;

import java.util.List;

/**
 * @author 杨秀眉
 */
public interface TranferTaskDao extends IBaseDao<ZzTransferTask> {

    /**
     * 通过取派员查询任务
     */
    public List<ZzTransferTask> getListByStafferId(String staffId);
}
