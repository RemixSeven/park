package com.ymwang.park.dto.Charge;

import lombok.Data;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/25
 */
@Data
public class DailyIncomeResponse {
    private List<DailyIncomeDto> dailyIncomeDtos;
    private String count;
}
