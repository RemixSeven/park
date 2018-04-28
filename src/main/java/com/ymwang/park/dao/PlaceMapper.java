package com.ymwang.park.dao;

import com.ymwang.park.model.Place;

public interface PlaceMapper {
    int deleteByPrimaryKey(String pId);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(String pId);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);
}