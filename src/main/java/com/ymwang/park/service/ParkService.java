package com.ymwang.park.service;

import com.ymwang.park.dto.Park.AddParkDto;
import com.ymwang.park.dto.Park.DeleteParkDto;
import com.ymwang.park.dto.Park.ParkDto;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
public interface ParkService {
    void addPark(AddParkDto addParkDto);
    void editPark(ParkDto parkDto);
    void deletePark(DeleteParkDto deleteParkDto);
    List<ParkDto> queryPark();
}
