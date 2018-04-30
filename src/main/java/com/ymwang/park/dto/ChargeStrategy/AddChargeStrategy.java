package com.ymwang.park.dto.ChargeStrategy;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Data
public class AddChargeStrategy {
    private String parkId;

    private Integer oneHour;

    private Integer threeHour;

    private Integer fiveHour;

    private Integer capping;
}
