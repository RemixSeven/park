package com.ymwang.park.controller;

import com.ymwang.park.dto.Car.*;
import com.ymwang.park.service.CarService;
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
@RequestMapping(value = "/car")
public class CarController {
    @Autowired
    CarService carService;
    @RequestMapping(method = RequestMethod.POST,value = "/addCar")
    public SingleResult<String> addCar(@RequestBody AddCarDto addCarDto){
        carService.addCar(addCarDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/editCar")
    public SingleResult<String> editCar(@RequestBody CarDto carDto){
        carService.editCar(carDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/deleteCar")
    public SingleResult<String> deleteCar(@RequestBody DeleteCar deleteCar){
        carService.deleteCar(deleteCar);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryCar")
    public SingleResult<List<CarDto>> queryCar(@RequestBody QueryCarDto queryCarDto){
        List<CarDto> carDtoList=carService.queryCar(queryCarDto);
        SingleResult<List<CarDto>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(carDtoList);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryCarInfo")
    public SingleResult<CarDto> queryCarInfo(@RequestBody CarInfoDto carInfoDto){
        CarDto queryCarDto=carService.queryCarInfo(carInfoDto);
        SingleResult<CarDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(queryCarDto);
        return response;
    }
}
