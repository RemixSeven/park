package com.ymwang.park.dto.ChargeStrategy;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Data
public class AddChargeStrategy {
    private String parkId;

    private int oneHour;

    private int threeHour;

    private int fiveHour;

    private int capping;
}
