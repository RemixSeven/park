package com.ymwang.park.service.impl;

import com.ymwang.park.dao.PlaceMapper;
import com.ymwang.park.dto.Place.*;
import com.ymwang.park.model.Place;
import com.ymwang.park.service.PlaceService;
import com.ymwang.park.utils.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        if (placeInfo.getReserveId()!=null&&placeInfo.getReserveId().length() != 0){
            throw new BizException("api.place.reserve.isReserve","预约失败,该车位已被预订");
        }
        if (placeInfo.getInuserId()!=null&&placeInfo.getInuserId().length() != 0){
            throw new BizException("api.place.reserve.isUsed","预约失败,该车位已被使用");
        }
        Place place=new Place();
        place.setpId(placeInfo.getpId());
        place.setReserveId(reservePlaceDto.getCarNumber());
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
            placeDto.setStatus("1");
            if (place.getReserveId()!=null&&place.getReserveId().length() != 0){
                placeDto.setStatus("2");
            }
            if (place.getInuserId()!=null&&place.getReserveId().length()!=0){
                placeDto.setStatus("3");
            }
            placeDtos.add(placeDto);
        }
        return placeDtos;
    }

    @Override
    public void parkPlace(ParkPlaceDto parkPlaceDto) {
        if (isReservePlace(parkPlaceDto)){
            Place reservePlace=placeMapper.reservePlace(parkPlaceDto.getCarNumber());
            if (reservePlace.getpNum()!=parkPlaceDto.getPNum()){
                throw new BizException("api.place.park.reserve","停车失败,停车位与预约车位不一致");
            }
        }
        if (isInusePlace(parkPlaceDto)){
            throw new BizException("api.place.isUsed","停车失败,该车位已被使用");
        }
        HashMap map=new HashMap();
        map.put("pNum",parkPlaceDto.getPNum());
        map.put("parkId",parkPlaceDto.getParkId());
        Place place=placeMapper.inusePlace(map);
        place.setReserveId(null);
        place.setInuserId(parkPlaceDto.getCarNumber());
        placeMapper.updateByPrimaryKey(place);
    }

    private boolean isInusePlace(ParkPlaceDto parkPlaceDto) {
        HashMap map=new HashMap();
        map.put("pNum",parkPlaceDto.getPNum());
        map.put("parkId",parkPlaceDto.getParkId());
        Place place=placeMapper.inusePlace(map);
        if (place.getInuserId()==null||place.getInuserId().length() == 0){
            return false;
        }
        return true;
    }

    private boolean isReservePlace(ParkPlaceDto parkPlaceDto) {
        if (placeMapper.reservePlace(parkPlaceDto.getCarNumber())==null){
            return false;
        }
        return true;
    }
}
