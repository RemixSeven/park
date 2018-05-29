package com.ymwang.park.dao;

import com.ymwang.park.dto.Charge.DailyIncomeDto;
import com.ymwang.park.dto.Charge.ParkIncome;
import com.ymwang.park.dto.Charge.ParkIncomeByParkId;
import com.ymwang.park.model.Charge;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public interface ChargeMapper {
    int deleteByPrimaryKey(String chargeId);

    int insert(Charge record);

    int insertSelective(Charge record);

    Charge selectByPrimaryKey(String chargeId);

    int updateByPrimaryKeySelective(Charge record);

    int updateByPrimaryKey(Charge record);
    List<Charge> queryCharge(String userId);
    List<DailyIncomeDto> queryDailyIncome( HashMap map);
    List<ParkIncomeByParkId> queryParkIncome(HashMap map);
    List<DailyIncomeDto> allParkDailyIncome(HashMap map);
    Charge parkCharge(String userId);
}