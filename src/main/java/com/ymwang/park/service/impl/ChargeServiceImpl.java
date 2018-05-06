package com.ymwang.park.service.impl;

import com.ymwang.park.dao.ChargeMapper;
import com.ymwang.park.dao.PlaceMapper;
import com.ymwang.park.dto.Charge.AddChargeDto;
import com.ymwang.park.model.Charge;
import com.ymwang.park.model.Place;
import com.ymwang.park.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public void addCharge(AddChargeDto addChargeDto) {
        Charge charge=new Charge();
        charge.setChargeId(UUID.randomUUID().toString().replaceAll("-", ""));
        charge.setCarNumber(addChargeDto.getCarNumber());
        charge.setEnterTime(addChargeDto.getEnterTime());
        charge.setUserName(addChargeDto.getUserName());
        charge.setOutTime(addChargeDto.getOutTime());
        charge.setCarNumber(addChargeDto.getCarNumber());
        charge.setParkId(addChargeDto.getParkId());
        charge.setUserId(addChargeDto.getUserId());
        charge.setMoney(addChargeDto.getMoney());
        chargeMapper.insertSelective(charge);
        Place place=new Place();
        place.setpId(addChargeDto.getPId());
        place.setInuserId(null);
        placeMapper.updateByPrimaryKeySelective(place);
    }
}
