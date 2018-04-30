package com.ymwang.park.service;

import com.ymwang.park.dto.Place.*;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/29
 */
public interface PlaceService {
    void addPlace(AddPlaceDto addPlaceDto);
    void reservePlace(ReservePlaceDto reservePlaceDto);
    void deletePlace(DeletePlaceDto deletePlaceDto);
    List<PlaceDto> queryPlace(QueryPlaceDto queryPlaceDto);
}
