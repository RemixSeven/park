package com.ymwang.park.model;

public class ChargeStrategy {
    private String strategyId;

    private String parkId;

    private Integer oneHour;

    private Integer threeHour;

    private Integer fiveHour;

    private Integer capping;

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId == null ? null : parkId.trim();
    }

    public Integer getOneHour() {
        return oneHour;
    }

    public void setOneHour(Integer oneHour) {
        this.oneHour = oneHour;
    }

    public Integer getThreeHour() {
        return threeHour;
    }

    public void setThreeHour(Integer threeHour) {
        this.threeHour = threeHour;
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