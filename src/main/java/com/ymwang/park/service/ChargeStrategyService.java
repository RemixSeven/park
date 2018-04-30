package com.ymwang.park.service;

import com.ymwang.park.dto.ChargeStrategy.AddChargeStrategy;
import com.ymwang.park.dto.ChargeStrategy.ChargeStrategyDto;
import com.ymwang.park.dto.ChargeStrategy.DeleteChargeStrategy;
import com.ymwang.park.dto.ChargeStrategy.QueryChargeStrategyDto;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
public interface ChargeStrategyService {
    void addChargeStrategy(AddChargeStrategy addChargeStrategy);
    void deleteChargeStrategy(DeleteChargeStrategy deleteChargeStrategy);
    void editChargeStrategy(ChargeStrategyDto chargeStrategyDto);
    ChargeStrategyDto queryChargeStrategy(QueryChargeStrategyDto queryChargeStrategyDto);
}
