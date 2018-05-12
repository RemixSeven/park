package com.ymwang.park.service.impl;

import com.ymwang.park.dao.ChargeMapper;

import com.ymwang.park.dao.ParkMapper;
import com.ymwang.park.dao.PlaceMapper;
import com.ymwang.park.dao.WalletMapper;

import com.ymwang.park.dto.Charge.AddChargeDto;
import com.ymwang.park.dto.Charge.QueryChargeRequest;
import com.ymwang.park.dto.Charge.QueryCharyDto;
import com.ymwang.park.model.Charge;

import com.ymwang.park.model.Park;
import com.ymwang.park.model.Place;
import com.ymwang.park.model.Wallet;

import com.ymwang.park.service.ChargeService;
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
public class ChargeServiceImpl implements ChargeService {
    @Autowired
    ChargeMapper chargeMapper;
    @Autowired
    PlaceMapper placeMapper;
    @Autowired
    WalletMapper walletMapper;
    @Autowired
    ParkMapper parkMapper;

    @Override
    public void addCharge(AddChargeDto addChargeDto) {
        Charge charge=new Charge();
        charge.setChargeId(UUID.randomUUID().toString().replaceAll("-", ""));
        charge.setCarNumber(addChargeDto.getCarNumber());
        charge.setEnterTime(addChargeDto.getEnterTime());
        charge.setOutTime(addChargeDto.getOutTime());
        charge.setUserName(addChargeDto.getUserName());
        charge.setCarNumber(addChargeDto.getCarNumber());
        charge.setParkId(addChargeDto.getParkId());
        charge.setUserId(addChargeDto.getUserId());
        charge.setMoney(addChargeDto.getMoney());
        chargeMapper.insertSelective(charge);
        Place place=placeMapper.selectByPrimaryKey(addChargeDto.getPId());
        place.setInuserId(null);
        placeMapper.updateByPrimaryKeySelective(place);
        Wallet wallet=walletMapper.queryWallet(addChargeDto.getUserId());
        wallet.setBalance(wallet.getBalance()-addChargeDto.getMoney());
        walletMapper.updateByPrimaryKeySelective(wallet);
    }

    @Override
    public List<QueryCharyDto> queryCharge(QueryChargeRequest queryChargeRequest) {
        List<QueryCharyDto> queryCharyDtos = new ArrayList<>();
        List<Charge> charges = chargeMapper.queryCharge(queryChargeRequest.getUserId());
        for (Charge charge : charges) {
            QueryCharyDto queryCharyDto = new QueryCharyDto();
            queryCharyDto.setUserId(charge.getUserId());
            queryCharyDto.setParkId(charge.getParkId());
            Park park = parkMapper.selectByPrimaryKey(charge.getParkId());
            queryCharyDto.setParkName(park.getParkName());
            queryCharyDto.setCarNumber(charge.getCarNumber());
            queryCharyDto.setMoney(charge.getMoney());
            queryCharyDto.setUserName(charge.getUserName());
            queryCharyDto.setEnterTime(charge.getEnterTime());
            queryCharyDto.setOutTime(charge.getOutTime());
            queryCharyDtos.add(queryCharyDto);
        }
        return queryCharyDtos;
    }
}
