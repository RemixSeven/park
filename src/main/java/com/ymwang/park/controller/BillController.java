package com.ymwang.park.controller;

import com.ymwang.park.dto.Bill.BillDto;
import com.ymwang.park.dto.Bill.DeleteBillDto;
import com.ymwang.park.dto.Bill.QueryBillDto;
import com.ymwang.park.service.BillService;
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
 * @Date: 2018/5/23
 */
@RestController
@RequestMapping(value = "/bill")
public class BillController {
    @Autowired
    BillService billService;
    @RequestMapping(method = RequestMethod.POST,value = "/deleteBill")
    public SingleResult<String> deleteBill(@RequestBody DeleteBillDto deleteBillDto){
        billService.deleteBill(deleteBillDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryBill")
    public SingleResult<List<BillDto>> queryBill(@RequestBody QueryBillDto queryBillDto){
        List<BillDto> billDtos=billService.queryBill(queryBillDto);
        SingleResult<List<BillDto>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(billDtos);
        return response;
    }
}
