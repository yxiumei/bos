package com.heihe.dao.Imp;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.heihe.dao.RoleDao;
import com.heihe.dao.Imp.base.Imp.IBaseDaoImp;
import com.heihe.domain.AuthRole;
import com.heihe.utils.PageBean;
@Repository
public class RoleDaoImp extends IBaseDaoImp<AuthRole> implements RoleDao {

}
