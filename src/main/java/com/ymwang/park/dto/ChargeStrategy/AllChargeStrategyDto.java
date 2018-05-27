package com.ymwang.park.dto.ChargeStrategy;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@Data
public class AllChargeStrategyDto {
    private String parkName;
    private Integer oneHour;

    private Integer threeHour;

    private Integer fiveHour;

    private Integer capping;
}
