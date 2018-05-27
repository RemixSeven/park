package com.ymwang.park.service;

import com.ymwang.park.dto.Park.*;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
public interface ParkService {
    void addPark(AddParkDto addParkDto);
    void editPark(ParkDto parkDto);
    void deletePark(DeleteParkDto deleteParkDto);
    List<QueryParkReponse> queryPark(QueryParkDto queryParkDto);
    List<ParkDto> queryParkByContent(QueryParkByContentDto queryParkByContentDto);
    AllParkResponse allPark(AllParkDto allParkDto);
}
