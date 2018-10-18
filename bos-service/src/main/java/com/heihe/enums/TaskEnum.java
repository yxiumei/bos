package com.heihe.enums;

import lombok.Data;

/**
 * 任务状态
 */
public enum TaskEnum {

    TASK_UNRECEIVED(0, "未拾取"),
    DISPATCHING(1 ,"派送中"),
    HAVE_SIGNED_IN(2, "已签收"),
    IS_STAFFER(1,"取派员"),
    DELE(1, "以删除"),
    NOT_DELE(0, "未删除"),
    NORMAL(0, "正常")
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
