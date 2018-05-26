package com.ymwang.park.service;

import com.ymwang.park.dto.Charge.*;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
public interface ChargeService {
    void addCharge(AddChargeDto addChargeDto);
    List<QueryCharyDto> queryCharge(QueryChargeRequest queryChargeRequest);
    DailyIncomeResponse queryDailyIncome(QueryDailyIncomeRequest queryDailyIncomeRequest);
    List<ParkIncome> queryParkIncome(QueryParkIncomeRequest queryParkIncomeRequest);
}
