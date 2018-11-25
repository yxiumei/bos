package com.heihe.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 杨秀眉
 */
@Data
public class ZzTransferTask {
    private String id;
    private Integer status;
    private String startPostion;
    private String endPostion;
    private String middiePostion;
    private String workId;
    private String staffId;
    private Timestamp taskTime;

}
