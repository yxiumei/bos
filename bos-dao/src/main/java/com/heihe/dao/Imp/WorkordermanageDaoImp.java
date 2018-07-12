package com.heihe.dao.Imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.Workordermanage;
import com.heihe.service.Imp.WorkordermanageDao;
import com.heihe.utils.PageBean;
@Repository
public class WorkordermanageDaoImp extends IBaseDaoImp<Workordermanage> implements
		WorkordermanageDao {

}
