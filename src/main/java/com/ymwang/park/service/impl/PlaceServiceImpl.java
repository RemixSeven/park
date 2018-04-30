package com.ymwang.park.service.impl;

import com.ymwang.park.dao.PlaceMapper;
import com.ymwang.park.dto.Place.*;
import com.ymwang.park.model.Place;
import com.ymwang.park.service.PlaceService;
import com.ymwang.park.utils.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/4/29
 */
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceMapper placeMapper;
    @Override
    public void addPlace(AddPlaceDto addPlaceDto) {
        Place place=new Place();
        place.setpId(UUID.randomUUID().toString().replaceAll("-", ""));
        place.setParkId(addPlaceDto.getParkId());
        place.setInuserId(null);
        place.setReserveId(null);
        place.setpNum(addPlaceDto.getPNum());
        placeMapper.insertSelective(place);
    }

    @Override
    public void reservePlace(ReservePlaceDto reservePlaceDto) {
        Place placeInfo=placeMapper.selectByPrimaryKey(reservePlaceDto.getPId());
        if (null!=placeInfo.getReserveId()||placeInfo.getReserveId().trim().length()!=0){
            throw new BizException("api.place.reserve.isReserve","预约失败,该车位已被预订");
        }
        if (null!=placeInfo.getInuserId()||placeInfo.getInuserId().trim().length()!=0){
            throw new BizException("api.place.reserve.isUsed","预约失败,该车位已被使用");
        }
        Place place=new Place();
        place.setpId(reservePlaceDto.getPId());
        place.setParkId(reservePlaceDto.getParkId());
        place.setpNum(reservePlaceDto.getPNum());
        place.setReserveId(reservePlaceDto.getUserId());
        placeMapper.updateByPrimaryKeySelective(place);
    }

    @Override
    public void deletePlace(DeletePlaceDto deletePlaceDto) {
        placeMapper.deleteByPrimaryKey(deletePlaceDto.getPId());
    }

    @Override
    public List<PlaceDto> queryPlace(QueryPlaceDto queryPlaceDto) {
        List<Place> places=placeMapper.queryPlace(queryPlaceDto.getParkId());
        List<PlaceDto> placeDtos=new ArrayList<>();
        for (Place place:places){
            PlaceDto placeDto=new PlaceDto();
            placeDto.setPId(place.getpId());
            placeDto.setParkId(place.getParkId());
            placeDto.setPNum(place.getpNum());
            placeDto.setReserveId(place.getReserveId());
            placeDto.setInuserId(place.getInuserId());
            placeDtos.add(placeDto);
        }
        return placeDtos;
    }
}
