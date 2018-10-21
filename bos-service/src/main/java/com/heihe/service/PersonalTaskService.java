package com.heihe.service;

import com.heihe.dto.PersonalTaskDto;

import java.util.List;

/**
 * @author 杨秀眉
 */
public interface PersonalTaskService {

    /**
     * 获取个人任务
     */
    public List<PersonalTaskDto> getList(String stafferId);

    /**
     * 获取所有任务
     */
    public  List<PersonalTaskDto> getAllList();
}
