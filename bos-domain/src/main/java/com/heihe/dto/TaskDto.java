package com.heihe.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 杨秀眉
 * 任务dto
 */

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

    public TaskDto() {
    }

    public TaskDto(String product, String taskNo, String safferNo, String safferName, String safferTelephone, String startCity, String arrivecity, String pickaddress, String status) {

        this.product = product;
        this.taskNo = taskNo;
        this.safferNo = safferNo;
        this.safferName = safferName;
        this.safferTelephone = safferTelephone;
        this.startCity = startCity;
        this.arrivecity = arrivecity;
        this.pickaddress = pickaddress;
        this.status = status;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getSafferNo() {
        return safferNo;
    }

    public void setSafferNo(String safferNo) {
        this.safferNo = safferNo;
    }

    public String getSafferName() {
        return safferName;
    }

    public void setSafferName(String safferName) {
        this.safferName = safferName;
    }

    public String getSafferTelephone() {
        return safferTelephone;
    }

    public void setSafferTelephone(String safferTelephone) {
        this.safferTelephone = safferTelephone;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getArrivecity() {
        return arrivecity;
    }

    public void setArrivecity(String arrivecity) {
        this.arrivecity = arrivecity;
    }

    public String getPickaddress() {
        return pickaddress;
    }

    public void setPickaddress(String pickaddress) {
        this.pickaddress = pickaddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
