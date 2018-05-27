package com.ymwang.park.service;

import com.ymwang.park.dto.ChargeStrategy.*;
import com.ymwang.park.dto.Park.AllParkDto;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
public interface ChargeStrategyService {
    void addChargeStrategy(AddChargeStrategy addChargeStrategy);
    void deleteChargeStrategy(DeleteChargeStrategy deleteChargeStrategy);
    void editChargeStrategy(ChargeStrategyDto chargeStrategyDto);
    ChargeStrategyDto queryChargeStrategy(QueryChargeStrategyDto queryChargeStrategyDto);
    AllChargeStrategyRe allChargeStrategy(AllParkDto allParkDto);
}
