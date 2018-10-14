package com.heihe.enums;

import lombok.Data;

/**
 * 任务状态
 */
public enum TaskEnum {

    TASK_UNRECEIVED(0, "未拾取"),
    DISPATCHING(1 ,"派送中"),
    HAVE_SIGNED_IN(2, "已签收")
    ;

    private Integer code;
    private String msg;

    TaskEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
