package com.ymwang.park.model;

public class Place {
    private String pId;

    private String parkId;

    private Integer pNum;

    private String reserveId;

    private String inuserId;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId == null ? null : parkId.trim();
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public String getReserveId() {
        return reserveId;
    }

    public void setReserveId(String reserveId) {
        this.reserveId = reserveId == null ? null : reserveId.trim();
    }

    public String getInuserId() {
        return inuserId;
    }

    public void setInuserId(String inuserId) {
        this.inuserId = inuserId == null ? null : inuserId.trim();
    }
}