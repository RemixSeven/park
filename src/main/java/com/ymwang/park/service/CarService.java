package com.ymwang.park.service;

import com.ymwang.park.dto.Car.*;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
public interface CarService {
    void addCar(AddCarDto addCarDto);
    void editCar(CarDto carDto);
    void deleteCar(DeleteCar deleteCar);
    List<CarDto> queryCar(QueryCarDto queryCarDto);
    CarDto queryCarInfo(CarInfoDto carInfoDto);
}
