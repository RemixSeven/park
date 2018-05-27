package com.ymwang.park.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymwang.park.dao.ChargeStrategyMapper;
import com.ymwang.park.dao.ParkMapper;
import com.ymwang.park.dto.ChargeStrategy.*;
import com.ymwang.park.dto.Park.AllParkDto;
import com.ymwang.park.model.ChargeStrategy;
import com.ymwang.park.model.Park;
import com.ymwang.park.service.ChargeStrategyService;
import com.ymwang.park.utils.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Service
public class ChargeStrategyServiceImpl implements ChargeStrategyService {
    @Autowired
    ChargeStrategyMapper chargeStrategyMapper;
    @Autowired
    ParkMapper parkMapper;

    @Override
    public void addChargeStrategy(AddChargeStrategy addChargeStrategy) {
        ChargeStrategy chargeStrategyInfo=chargeStrategyMapper.queryChargeStrategy(addChargeStrategy.getParkId());
        if (chargeStrategyInfo!=null){
            throw new BizException("api.chargeStrategy.park.unique","一个停车场只能有一个收费策略");
        }
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
        chargeStrategyDto.setOneHour(chargeStrategy.getOneHour());
        chargeStrategyDto.setThreeHour(chargeStrategy.getThreeHour());
        chargeStrategyDto.setFiveHour(chargeStrategy.getFiveHour());
        chargeStrategyDto.setCapping(chargeStrategy.getCapping());
        return chargeStrategyDto;
    }

    @Override
    public AllChargeStrategyRe allChargeStrategy(AllParkDto allParkDto) {
        AllChargeStrategyRe allChargeStrategyRe=new AllChargeStrategyRe();
        PageHelper.startPage(allParkDto.getPageNum(),allParkDto.getPageSize());
        List<ChargeStrategy> chargeStrategies=chargeStrategyMapper.allChargeStrategy();
        List<AllChargeStrategyDto> allChargeStrategyDtos=new ArrayList<>();
        for (ChargeStrategy chargeStrategy:chargeStrategies){
            AllChargeStrategyDto allChargeStrategyDto=new AllChargeStrategyDto();
            Park park =parkMapper.selectByPrimaryKey(chargeStrategy.getParkId());
            allChargeStrategyDto.setParkName(park.getParkName());
            allChargeStrategyDto.setOneHour(chargeStrategy.getOneHour());
            allChargeStrategyDto.setThreeHour(chargeStrategy.getThreeHour());
            allChargeStrategyDto.setFiveHour(chargeStrategy.getFiveHour());
            allChargeStrategyDto.setCapping(chargeStrategy.getCapping());
            allChargeStrategyDtos.add(allChargeStrategyDto);
        }
        PageInfo<AllChargeStrategyDto> pageInfo=new PageInfo<AllChargeStrategyDto>(allChargeStrategyDtos);
        long total=pageInfo.getTotal();
        allChargeStrategyRe.setCount(String.valueOf(total));
        allChargeStrategyRe.setAllChargeStrategyDtos(allChargeStrategyDtos);
        return allChargeStrategyRe;
    }
}
