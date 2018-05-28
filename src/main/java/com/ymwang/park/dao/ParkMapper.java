package com.ymwang.park.dao;

import com.ymwang.park.model.Park;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkMapper {
    int deleteByPrimaryKey(String parkId);

    int insert(Park record);

    int insertSelective(Park record);

    Park selectByPrimaryKey(String parkId);

    int updateByPrimaryKeySelective(Park record);

    int updateByPrimaryKey(Park record);
    List<Park> queryPark();
    List<Park> queryParkByContent(String parkName);
    Park selectByParkName(String parkName);
}