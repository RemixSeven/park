package com.ymwang.park.controller;

import com.ymwang.park.dto.Charge.*;
import com.ymwang.park.dto.Park.AllParkDto;
import com.ymwang.park.dto.User.QueryUserRequest;
import com.ymwang.park.service.ChargeService;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@RestController
@RequestMapping(value = "/charge")
public class ChargeController {
    @Autowired
    ChargeService chargeService;
    @RequestMapping(method = RequestMethod.POST,value = "/addCharge")
    public SingleResult<ChargeDto> addCharge(@RequestBody AddChargeDto addChargeDto){
        ChargeDto chargeDto=chargeService.addCharge(addChargeDto);
        SingleResult<ChargeDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(chargeDto);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/queryCharge")
    public SingleResult<List<QueryCharyDto>> queryCharge(@RequestBody QueryChargeRequest queryChargeRequest){
        List<QueryCharyDto> queryCharyDtoList=chargeService.queryCharge(queryChargeRequest);
        SingleResult<List<QueryCharyDto> > response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryCharyDtoList);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryDailyIncome")
    public SingleResult<DailyIncomeResponse> queryDailyIncome(@RequestBody QueryDailyIncomeRequest queryDailyIncomeRequest){
        DailyIncomeResponse dailyIncomeResponse=chargeService.queryDailyIncome(queryDailyIncomeRequest);
        SingleResult<DailyIncomeResponse> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(dailyIncomeResponse);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryParkIncome")
    public SingleResult<List<ParkIncome>> queryParkIncome(@RequestBody QueryParkIncomeRequest queryDailyIncomeRequest){
        List<ParkIncome> parkIncomes=chargeService.queryParkIncome(queryDailyIncomeRequest);
        SingleResult<List<ParkIncome>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(parkIncomes);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/allParkDailyIncome")
   public SingleResult<DailyIncomeResponse> allParkDailyIncome(@RequestBody AllParkDto allParkDto)
    {
        DailyIncomeResponse dailyIncomeResponse=chargeService.allParkDailyIncome(allParkDto);
        SingleResult<DailyIncomeResponse> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(dailyIncomeResponse);
        return response;
    }
}
