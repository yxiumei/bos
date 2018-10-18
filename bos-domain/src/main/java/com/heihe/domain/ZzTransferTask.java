package com.heihe.domain;

public class ZzTransferTask {
    private String id;
    private Integer status;
    private String startPostion;
    private String endPostion;
    private String middiePostion;
    private String workId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartPostion() {
        return startPostion;
    }

    public void setStartPostion(String startPostion) {
        this.startPostion = startPostion;
    }

    public String getEndPostion() {
        return endPostion;
    }

    public void setEndPostion(String endPostion) {
        this.endPostion = endPostion;
    }

    public String getMiddiePostion() {
        return middiePostion;
    }

    public void setMiddiePostion(String middiePostion) {
        this.middiePostion = middiePostion;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }


}
