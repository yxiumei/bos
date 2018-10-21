package com.heihe.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨秀眉
 * 任务dto
 */
@Data
public class TaskDto implements Serializable{

    private static final long serialVersionUID = 6487667516693563665L;
    private String product;
    private String taskNo;
    private String safferNo;
    private String safferName;
    private String safferTelephone;
    private String startCity;
    private String arrivecity;
    private String pickaddress;
    /**
     * 1 ：任务未领取   2： 配送中  3： 已签收
     */
    private String status;
}
