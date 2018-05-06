package com.ymwang.park.controller;

import com.ymwang.park.dto.Place.*;
import com.ymwang.park.service.PlaceService;
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
 * @Date: 2018/4/29
 */
@RestController
@RequestMapping(value = "/place")
public class PlaceController {
    @Autowired
    PlaceService placeService;
    @RequestMapping(method = RequestMethod.POST,value = "/addPlace")
    public SingleResult<String> addPlace(@RequestBody AddPlaceDto addPlaceDto){
        placeService.addPlace(addPlaceDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/reservePlace")
    public SingleResult<String> reservePlace(@RequestBody ReservePlaceDto reservePlaceDto){
        placeService.reservePlace(reservePlaceDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/deletePlace")
    public SingleResult<String> deletePlace(@RequestBody DeletePlaceDto deletePlaceDto){
        placeService.deletePlace(deletePlaceDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryPlace")
    public SingleResult<List<PlaceDto>> queryPlace(@RequestBody QueryPlaceDto queryPlaceDto){
        List<PlaceDto> placeDtos=placeService.queryPlace(queryPlaceDto);
        SingleResult<List<PlaceDto>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(placeDtos);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/parkPlace")
    public SingleResult<String> parkPlace(@RequestBody ParkPlaceDto parkPlaceDto){
        placeService.parkPlace(parkPlaceDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
}
