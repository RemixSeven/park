package com.ymwang.park.controller;

import com.ymwang.park.dto.Park.AddParkDto;
import com.ymwang.park.dto.Park.DeleteParkDto;
import com.ymwang.park.dto.Park.ParkDto;
import com.ymwang.park.service.ParkService;
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
    public SingleResult<List<ParkDto>> queryPark(){
        List<ParkDto> parkDtos=parkService.queryPark();
        SingleResult<List<ParkDto>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(parkDtos);
        return response;
    }
}
