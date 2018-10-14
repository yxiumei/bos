package com.heihe.service;

import com.heihe.dto.TaskDto;
import com.heihe.utils.PageBean;

public interface TaskServer {
    /**
     * 分页查询
     * @param pageBean
     */
    PageBean pageQuery(PageBean pageBean);
}
