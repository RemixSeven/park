package com.ymwang.park.dao;

import com.ymwang.park.model.ChargeStrategy;

public interface ChargeStrategyMapper {
    int deleteByPrimaryKey(Integer strategyId);

    int insert(ChargeStrategy record);

    int insertSelective(ChargeStrategy record);

    ChargeStrategy selectByPrimaryKey(Integer strategyId);

    int updateByPrimaryKeySelective(ChargeStrategy record);

    int updateByPrimaryKey(ChargeStrategy record);
}