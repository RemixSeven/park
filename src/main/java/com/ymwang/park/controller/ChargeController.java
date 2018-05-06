package com.ymwang.park.controller;

import com.ymwang.park.dto.Charge.AddChargeDto;
import com.ymwang.park.service.ChargeService;
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
@RequestMapping(value = "/charge")
public class ChargeController {
    @Autowired
    ChargeService chargeService;
    @RequestMapping(method = RequestMethod.POST,value = "/addCharge")
    public SingleResult<String> addCharge(@RequestBody AddChargeDto addChargeDto){
        chargeService.addCharge(addChargeDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }

}
