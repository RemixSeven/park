package com.ymwang.park.dao;

import com.ymwang.park.model.Place;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceMapper {
    int deleteByPrimaryKey(String pId);

    int insert(Place record);

    int insertSelective(Place record);

    Place selectByPrimaryKey(String pId);

    int updateByPrimaryKeySelective(Place record);

    int updateByPrimaryKey(Place record);
    List<Place> queryPlace(String parkId);
}