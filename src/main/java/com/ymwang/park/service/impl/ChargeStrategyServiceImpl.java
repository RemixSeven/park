package com.ymwang.park.service.impl;

import com.ymwang.park.dao.ChargeStrategyMapper;
import com.ymwang.park.dto.ChargeStrategy.AddChargeStrategy;
import com.ymwang.park.dto.ChargeStrategy.ChargeStrategyDto;
import com.ymwang.park.dto.ChargeStrategy.DeleteChargeStrategy;
import com.ymwang.park.dto.ChargeStrategy.QueryChargeStrategyDto;
import com.ymwang.park.model.ChargeStrategy;
import com.ymwang.park.service.ChargeStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Service
public class ChargeStrategyServiceImpl implements ChargeStrategyService {
    @Autowired
    ChargeStrategyMapper chargeStrategyMapper;

    @Override
    public void addChargeStrategy(AddChargeStrategy addChargeStrategy) {
        ChargeStrategy chargeStrategy=new ChargeStrategy();
        chargeStrategy.setStrategyId(UUID.randomUUID().toString().replaceAll("-", ""));
        chargeStrategy.setParkId(addChargeStrategy.getParkId());
        chargeStrategy.setOneHour(addChargeStrategy.getOneHour());
        chargeStrategy.setThreeHour(addChargeStrategy.getThreeHour());
        chargeStrategy.setFiveHour(addChargeStrategy.getFiveHour());
        chargeStrategy.setCapping(addChargeStrategy.getCapping());
        chargeStrategyMapper.insertSelective(chargeStrategy);
    }

    @Override
    public void deleteChargeStrategy(DeleteChargeStrategy deleteChargeStrategy) {
        chargeStrategyMapper.deleteByPrimaryKey(deleteChargeStrategy.getStrategyId());
    }

    @Override
    public void editChargeStrategy(ChargeStrategyDto chargeStrategyDto) {
        ChargeStrategy chargeStrategy= chargeStrategyMapper.selectByPrimaryKey(chargeStrategyDto.getStrategyId());
        chargeStrategy.setOneHour(chargeStrategyDto.getOneHour());
        chargeStrategy.setThreeHour(chargeStrategyDto.getThreeHour());
        chargeStrategy.setFiveHour(chargeStrategyDto.getFiveHour());
        chargeStrategy.setCapping(chargeStrategyDto.getCapping());
        chargeStrategyMapper.updateByPrimaryKeySelective(chargeStrategy);
    }

    @Override
    public ChargeStrategyDto queryChargeStrategy(QueryChargeStrategyDto queryChargeStrategyDto) {
        ChargeStrategy chargeStrategy=chargeStrategyMapper.queryChargeStrategy(queryChargeStrategyDto.getParkId());
        ChargeStrategyDto chargeStrategyDto=new ChargeStrategyDto();
        chargeStrategyDto.setStrategyId(chargeStrategy.getStrategyId());
        chargeStrategyDto.setParkId(chargeStrategy.getParkId());
        chargeStrategyDto.setOneHour(chargeStrategy.getOneHour());
        chargeStrategyDto.setThreeHour(chargeStrategy.getThreeHour());
        chargeStrategyDto.setFiveHour(chargeStrategy.getFiveHour());
        chargeStrategyDto.setCapping(chargeStrategy.getCapping());
        return chargeStrategyDto;
    }
}
