package com.ymwang.park.controller;

import com.ymwang.park.dto.ChargeStrategy.*;
import com.ymwang.park.dto.Park.AllParkDto;
import com.ymwang.park.service.ChargeStrategyService;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@RestController
@RequestMapping(value = "/chargeStrategy")
public class ChargeStrategyController {
    @Autowired
    ChargeStrategyService chargeStrategyService;
    @RequestMapping(method = RequestMethod.POST,value = "/addChargeStrategy")
    public SingleResult<String> addChargeStrategy(@RequestBody AddChargeStrategy addChargeStrategy){
        chargeStrategyService.addChargeStrategy(addChargeStrategy);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/deleteChargeStrategy")
    public SingleResult<String> deleteChargeStrategy(@RequestBody DeleteChargeStrategy deleteChargeStrategy){
        chargeStrategyService.deleteChargeStrategy(deleteChargeStrategy);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/editChargeStrategy")
    public SingleResult<String> editChargeStrategy(@RequestBody ChargeStrategyDto chargeStrategyDto){
        chargeStrategyService.editChargeStrategy(chargeStrategyDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryChargeStrategy")
    public SingleResult<ChargeStrategyDto> queryChargeStrategy(@RequestBody QueryChargeStrategyDto queryChargeStrategyDto){
        ChargeStrategyDto chargeStrategyDto=chargeStrategyService.queryChargeStrategy(queryChargeStrategyDto);
        SingleResult<ChargeStrategyDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(chargeStrategyDto);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/allChargeStrategy")
    public SingleResult<AllChargeStrategyRe> allChargeStrategy(@RequestBody AllParkDto allParkDto){
        AllChargeStrategyRe allChargeStrategyRe=chargeStrategyService.allChargeStrategy(allParkDto);
        SingleResult<AllChargeStrategyRe> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(allChargeStrategyRe);
        return response;
    }
}
