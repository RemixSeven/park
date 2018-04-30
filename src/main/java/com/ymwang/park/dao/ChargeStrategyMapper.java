package com.ymwang.park.dao;

import com.ymwang.park.model.ChargeStrategy;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeStrategyMapper {
    int deleteByPrimaryKey(String strategyId);

    int insert(ChargeStrategy record);

    int insertSelective(ChargeStrategy record);

    ChargeStrategy selectByPrimaryKey(String strategyId);

    int updateByPrimaryKeySelective(ChargeStrategy record);

    int updateByPrimaryKey(ChargeStrategy record);
    ChargeStrategy queryChargeStrategy(String parkId);
}