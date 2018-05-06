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
        if (placeInfo.getReserveId()!=null&&!"".equals(placeInfo.getReserveId().trim())){
            throw new BizException("api.place.reserve.isReserve","预约失败,该车位已被预订");
        }
        if (placeInfo.getInuserId()!=null&&!"".equals(placeInfo.getInuserId().trim())){
            throw new BizException("api.place.reserve.isUsed","预约失败,该车位已被使用");
        }
        Place place=new Place();
        place.setpId(placeInfo.getpId());
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

    @Override
    public void parkPlace(ParkPlaceDto parkPlaceDto) {
        if (isReservePlace(parkPlaceDto)){
            Place reservePlace=placeMapper.reservePlace(parkPlaceDto.getUserId());
            if (reservePlace.getpNum()!=parkPlaceDto.getPNum()){
                throw new BizException("api.place.park.reserve","停车失败,停车位与预约车位不一致");
            }
        }
        if (isInusePlace(parkPlaceDto)){
            throw new BizException("api.place.isUsed","停车失败,该车位已被使用");
        }
        Place place=placeMapper.inusePlace(parkPlaceDto.getPNum(),parkPlaceDto.getParkId());
        place.setReserveId(null);
        place.setInuserId(parkPlaceDto.getUserId());
        placeMapper.updateByPrimaryKeySelective(place);
    }

    private boolean isInusePlace(ParkPlaceDto parkPlaceDto) {
        if (placeMapper.inusePlace(parkPlaceDto.getPNum(),parkPlaceDto.getParkId())==null){
            return false;
        }
        return true;
    }

    private boolean isReservePlace(ParkPlaceDto parkPlaceDto) {
        if (placeMapper.reservePlace(parkPlaceDto.getUserId())==null){
            return false;
        }
        return true;
    }
}
