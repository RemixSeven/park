package com.ymwang.park.model;

public class ChargeStrategy {
    private Integer strategyId;

    private String parkId;

    private Integer oneHour2;

    private Integer threeHour2;

    private Integer fiveHour;

    private Integer capping;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId == null ? null : parkId.trim();
    }

    public Integer getOneHour2() {
        return oneHour2;
    }

    public void setOneHour2(Integer oneHour2) {
        this.oneHour2 = oneHour2;
    }

    public Integer getThreeHour2() {
        return threeHour2;
    }

    public void setThreeHour2(Integer threeHour2) {
        this.threeHour2 = threeHour2;
    }

    public Integer getFiveHour() {
        return fiveHour;
    }

    public void setFiveHour(Integer fiveHour) {
        this.fiveHour = fiveHour;
    }

    public Integer getCapping() {
        return capping;
    }

    public void setCapping(Integer capping) {
        this.capping = capping;
    }
}