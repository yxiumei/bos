package com.heihe.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 个人任务组Dto
 * @author 杨秀眉
 */

@Data
public class PersonalTaskDto implements Serializable{

    private static final long serialVersionUID = 7132241708443708505L;
    /** 任务id */
    private String taskId;
    /**
     * 任务状态 0：任务未领取   1： 配送中  2： 已签收
     */
    private Integer status;
    /** 起始地 */
    private String startPostion;
    /** 目的地 */
    private String endPostion;
    /** 中转地 */
    private String middiePostion;
    /** 商品 */
    private String productName;
    /** 取派员id */
    private String staffId;
    /** 派送员姓名 */
    private String staffName;
    /** 拾取任务时间 */
    private Timestamp taskTime;
    /** 客户id */
    private String customerId;
    /** 客户姓名 */
    private String customerName;
    /** 收件人 */
    private String receiver;
    /** 收件人地址 */
    private String receiverAddress;
    /** 客户电话 */
    private String telephone;
    /** 客户备注 */
    private String remark;
    /** 拾取任务时间字符串类型 */
    private String taskTimeStr;

    public String getTaskTimeStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp taskTime = this.taskTime;
        Date date = new Date(taskTime.getTime());
        String format1 = format.format(date);
        return format1;
    }
}
