package com.heihe.domain;

import lombok.Data;

/**
 * 中转信息
 */
@Data
public class TransferTask {

    private Integer id;
    /**
     * 0：待揽件 1：运输中 2：派送中 3：已签收
     */
    private Integer status;
    /**
     * 起始地
     */
    private String startPostion;
    /**
     * 到达地
     */
    private String endPostion;
    /**
     * 中转地
     */
    private String middiePostion;


}
