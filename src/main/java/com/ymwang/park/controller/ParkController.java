package com.ymwang.park.controller;

import com.ymwang.park.dto.Park.*;
import com.ymwang.park.service.ParkService;
import com.ymwang.park.utils.DateUtils;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
@RestController
@RequestMapping(value = "/park")
public class ParkController {
    @Autowired
    ParkService parkService;
    @RequestMapping(method = RequestMethod.POST,value = "/addPark")
    public SingleResult<String> addPark(@RequestBody AddParkDto addParkDto){
        parkService.addPark(addParkDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        Date date=new Date();
        date.getTime();
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/editPark")
    public SingleResult<String> editPark(@RequestBody ParkDto parkDto){
        parkService.editPark(parkDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/deletePark")
    public SingleResult<String> deletePark(@RequestBody DeleteParkDto deleteParkDto){
        parkService.deletePark(deleteParkDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryPark")
    public SingleResult<List<QueryParkReponse>> queryPark(@RequestBody QueryParkDto queryParkDto){
        List<QueryParkReponse> queryParkReponses=parkService.queryPark(queryParkDto);
        SingleResult<List<QueryParkReponse>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryParkReponses);
        return response;
    }
}
