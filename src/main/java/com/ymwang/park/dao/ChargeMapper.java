package com.ymwang.park.dao;

import com.ymwang.park.model.Charge;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeMapper {
    int deleteByPrimaryKey(String chargeId);

    int insert(Charge record);

    int insertSelective(Charge record);

    Charge selectByPrimaryKey(String chargeId);

    int updateByPrimaryKeySelective(Charge record);

    int updateByPrimaryKey(Charge record);
}