package com.ymwang.park.dao;

import com.ymwang.park.model.Place;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
    Place reservePlace(String reserveId);
    Place inusePlace(HashMap map);
    int placeTotal(@Param("parkId")String parkId);
    int placeSurplus(@Param("parkId")String parkId);
}