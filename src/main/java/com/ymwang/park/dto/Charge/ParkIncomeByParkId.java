package com.ymwang.park.dto.Charge;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/26
 */
@Data
public class ParkIncomeByParkId {
    private String parkId;
    private int money;
}
