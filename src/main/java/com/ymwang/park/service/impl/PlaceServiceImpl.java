package com.ymwang.park.service.impl;

import com.ymwang.park.dao.*;
import com.ymwang.park.dto.Place.*;
import com.ymwang.park.model.*;
import com.ymwang.park.service.PlaceService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.ymwang.park.utils.DateUtils.getDate;

/**
 * @Author: wym
 * @Date: 2018/4/29
 */
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceMapper placeMapper;
    @Autowired
    ChargeMapper chargeMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    ParkMapper parkMapper;
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
        Place place=placeMapper.reservePlace(parkPlaceDto.getUserId());
        User user=userMapper.selectByPrimaryKey(parkPlaceDto.getUserId());
        List<Car> cars=carMapper.queryCar(parkPlaceDto.getUserId());
        if (cars.size()==0){
            throw new BizException("api.car.noBind","您尚未绑定车辆，请绑定车辆之后才能停车");
        }
        Car car=cars.get(0);
        Charge charge=new Charge();
        charge.setChargeId(UUID.randomUUID().toString().replaceAll("-", ""));
        charge.setCarNumber(car.getCarNumber());
        charge.setEnterTime(DateUtils.parseDate(getDate("yyyy-MM-dd HH:mm:ss")));
        charge.setUserName(user.getUserName());
        charge.setParkId(place.getParkId());
        charge.setUserId(parkPlaceDto.getUserId());
        charge.setMoney(0);
        charge.setValid("2");
        chargeMapper.insert(charge);
        place.setReserveId(null);
        place.setInuserId(parkPlaceDto.getUserId());
        placeMapper.updateByPrimaryKey(place);
    }

    @Override
    public ParkStatus queryPark(ParkPlaceDto parkPlaceDto) {
        ParkStatus parkStatus=new ParkStatus();
        parkStatus.setStatus("3");
        Charge charge=chargeMapper.parkCharge(parkPlaceDto.getUserId());
        if (charge!=null){
            parkStatus.setEnterTime(charge.getEnterTime());
            parkStatus.setStatus("1");
        }
        Place place=placeMapper.reservePlace(parkPlaceDto.getUserId());
        if (place!=null){
            parkStatus.setStatus("2");
        }
        return parkStatus;
    }

    @Override
    public ReservationDto myReservation(ParkPlaceDto parkPlaceDto) {
        ReservationDto reservationDto=new ReservationDto();
        Place place=placeMapper.reservePlace(parkPlaceDto.getUserId());
        if (place!=null) {
            Park park = parkMapper.selectByPrimaryKey(place.getParkId());
            reservationDto.setParkName(park.getParkName());
            reservationDto.setPId(place.getpId());
            reservationDto.setPNum(place.getpNum());
        }
        return reservationDto;
    }

    @Override
    public void cancelReserve(DeletePlaceDto deletePlaceDto) {
        Place place=placeMapper.selectByPrimaryKey(deletePlaceDto.getPId());
        place.setReserveId(null);
        placeMapper.updateByPrimaryKey(place);
    }


    private boolean isReservePlace(ParkPlaceDto parkPlaceDto) {
        if (placeMapper.reservePlace(parkPlaceDto.getUserId())==null){
            return false;
        }
        return true;
    }
}
