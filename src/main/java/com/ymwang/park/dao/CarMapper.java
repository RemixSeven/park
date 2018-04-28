package com.ymwang.park.dao;

import com.ymwang.park.model.Car;

public interface CarMapper {
    int deleteByPrimaryKey(String carNumber);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String carNumber);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
}