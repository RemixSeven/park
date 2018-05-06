package com.ymwang.park.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Park {
    private String parkId;

    private String parkName;

    private String parkAddress;

    private String parkDetail;
    @JSONField(format="HH:mm:ss")
    private Date openTime;
    @JSONField(format="HH:mm:ss")
    private Date closeTime;

    private String longitude;

    private String latitude;

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId == null ? null : parkId.trim();
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName == null ? null : parkName.trim();
    }

    public String getParkAddress() {
        return parkAddress;
    }

    public void setParkAddress(String parkAddress) {
        this.parkAddress = parkAddress == null ? null : parkAddress.trim();
    }

    public String getParkDetail() {
        return parkDetail;
    }

    public void setParkDetail(String parkDetail) {
        this.parkDetail = parkDetail == null ? null : parkDetail.trim();
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }
}