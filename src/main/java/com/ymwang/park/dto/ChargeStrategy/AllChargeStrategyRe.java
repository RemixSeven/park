package com.ymwang.park.dto.ChargeStrategy;

import lombok.Data;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@Data
public class AllChargeStrategyRe {
    private List<AllChargeStrategyDto> allChargeStrategyDtos;
    private String count;
    private int sum;
}
