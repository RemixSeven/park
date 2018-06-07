package com.ymwang.park.service.impl;

import com.ymwang.park.dao.CarMapper;
import com.ymwang.park.dto.Car.*;
import com.ymwang.park.model.Car;
import com.ymwang.park.service.CarService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.PatternUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarMapper carMapper;
    @Override
    public void addCar(AddCarDto addCarDto) {
        if (StringUtils.isEmpty(addCarDto.getCarNumber())||StringUtils.isEmpty(addCarDto.getCarColor())||StringUtils.isEmpty(addCarDto.getCarName())){
            throw new BizException("api.car.empty","车辆信息都为必填项");
        }
        if(!PatternUtil.checkCarNumber(addCarDto.getCarNumber())){
            throw new BizException("api.car.carNumber", "车牌号格式错误");
        }
        List<Car> cars=carMapper.queryCar(addCarDto.getUserId());
        if (cars.size()>=1){
            throw new BizException("api.car.carNumber.exist", "一个用户只能绑定一辆车");
        }
        Car car=new Car();
        car.setCarId(UUID.randomUUID().toString().replaceAll("-", ""));
        car.setCarNumber(addCarDto.getCarNumber());
        car.setUserId(addCarDto.getUserId());
        car.setCarName(addCarDto.getCarName());
        car.setCarColor(addCarDto.getCarColor());
        carMapper.insertSelective(car);
    }
    private boolean isCarNumberExist(String  carNumber) {
        if ((carMapper.selectByCarNumber(carNumber))==null)
        {
            return false;
        }
        return true;
    }
    @Override
    public void editCar(CarDto carDto) {
        if (StringUtils.isEmpty(carDto.getCarNumber())||StringUtils.isEmpty(carDto.getCarColor())||StringUtils.isEmpty(carDto.getCarName())){
            throw new BizException("api.car.empty","车辆信息都为必填项");
        }
        if(PatternUtil.checkCarNumber(carDto.getCarNumber())==false){
            throw new BizException("api.car.carNumber", "车牌号格式错误");
        }
        if (isCarNumberExist(carDto.getCarNumber())){
            throw new BizException("api.car.carNumber.exist", "该车已绑定，请勿重复添加");
        }

        Car car=new Car();
        car.setCarId(carDto.getCarId());
        car.setCarNumber(carDto.getCarNumber());
        car.setUserId(carDto.getUserId());
        car.setCarName(carDto.getCarName());
        car.setCarColor(carDto.getCarColor());
        carMapper.updateByPrimaryKey(car);
    }

    @Override
    public void deleteCar(DeleteCar deleteCar) {
        carMapper.deleteByPrimaryKey(deleteCar.getCarId());
    }

    @Override
    public List<CarDto> queryCar(QueryCarDto queryCarDto) {
        List<CarDto> carDtoList=new ArrayList<CarDto>();
        List<Car> cars=carMapper.queryCar(queryCarDto.getUserId());
        for (Car car:cars){
            CarDto carDto=new CarDto();
            carDto.setCarId(car.getCarId());
            carDto.setUserId(car.getUserId());
            carDto.setCarNumber(car.getCarNumber());
            carDto.setCarName(car.getCarName());
            carDto.setCarColor(car.getCarColor());
            carDtoList.add(carDto);
        }
        return carDtoList;
    }

    @Override
    public CarDto queryCarInfo(CarInfoDto carInfoDto) {
        CarDto carDto=new CarDto();
        Car car=carMapper.selectByPrimaryKey(carInfoDto.getCarId());
        if (null == car){
            throw new BizException("api.car.noExist", "不存在该车辆");
        }
        carDto.setCarId(car.getCarId());
        carDto.setUserId(car.getUserId());
        carDto.setCarNumber(car.getCarNumber());
        carDto.setCarName(car.getCarName());
        carDto.setCarColor(car.getCarColor());
        return carDto;
    }
}
