package com.ymwang.park.model;

import java.util.Date;

public class CouponDeploy extends CouponDeployKey {
    private Date activiStartTime;

    private Date activiEndTime;

    private Date effiStartTime;

    private Date effiEndTime;


    public Date getActiviStartTime() {
        return activiStartTime;
    }

    public void setActiviStartTime(Date activiStartTime) {
        this.activiStartTime = activiStartTime;
    }

    public Date getActiviEndTime() {
        return activiEndTime;
    }

    public void setActiviEndTime(Date activiEndTime) {
        this.activiEndTime = activiEndTime;
    }

    public Date getEffiStartTime() {
        return effiStartTime;
    }

    public void setEffiStartTime(Date effiStartTime) {
        this.effiStartTime = effiStartTime;
    }

    public Date getEffiEndTime() {
        return effiEndTime;
    }

    public void setEffiEndTime(Date effiEndTime) {
        this.effiEndTime = effiEndTime;
    }


}