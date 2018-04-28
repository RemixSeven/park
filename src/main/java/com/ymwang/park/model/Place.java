package com.ymwang.park.model;

public class Place {
    private String pId;

    private String parkId;

    private Integer pNum;

    private String isReserve;

    private String isInuse;

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

    public String getIsReserve() {
        return isReserve;
    }

    public void setIsReserve(String isReserve) {
        this.isReserve = isReserve == null ? null : isReserve.trim();
    }

    public String getIsInuse() {
        return isInuse;
    }

    public void setIsInuse(String isInuse) {
        this.isInuse = isInuse == null ? null : isInuse.trim();
    }
}