package com.ymwang.park.service.impl;

import com.ymwang.park.dto.Park.AddParkDto;
import com.ymwang.park.dto.Park.DeleteParkDto;
import com.ymwang.park.dto.Park.ParkDto;
import com.ymwang.park.dao.ParkMapper;
import com.ymwang.park.model.Park;
import com.ymwang.park.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
@Service
public class ParkServiceImpl implements ParkService {
    @Autowired
    ParkMapper parkMapper;

    @Override
    public void addPark(AddParkDto addParkDto) {
        Park park=new Park();
        park.setParkId(UUID.randomUUID().toString().replaceAll("-", ""));
        park.setParkName(addParkDto.getParkName());
        park.setParkAddress(addParkDto.getParkAddress());
        park.setOpenTime(addParkDto.getOpenTime());
        park.setCloseTime(addParkDto.getCloseTime());
        park.setLatitude(addParkDto.getLatitude());
        park.setLongitude(addParkDto.getLongitude());
        parkMapper.insertSelective(park);
    }

    @Override
    public void editPark(ParkDto parkDto) {
        Park park=new Park();
        park.setParkId(parkDto.getParkId());
        park.setParkName(parkDto.getParkName());
        park.setParkAddress(parkDto.getParkAddress());
        park.setOpenTime(parkDto.getOpenTime());
        park.setCloseTime(parkDto.getCloseTime());
        park.setLatitude(parkDto.getLatitude());
        park.setLongitude(parkDto.getLongitude());
        parkMapper.updateByPrimaryKeySelective(park);
    }

    @Override
    public void deletePark(DeleteParkDto deleteParkDto) {
        parkMapper.deleteByPrimaryKey(deleteParkDto.getParkId());
    }

    @Override
    public List<ParkDto> queryPark() {
        List<ParkDto> parkDtos=new ArrayList<>();
        List<Park> parks=parkMapper.queryPark();
        for (Park park:parks){
            ParkDto parkDto=new ParkDto();
            parkDto.setParkId(park.getParkId());
            parkDto.setParkName(park.getParkName());
            parkDto.setParkAddress(park.getParkAddress());
            parkDto.setParkDetail(park.getParkDetail());
            parkDto.setOpenTime(park.getOpenTime());
            parkDto.setCloseTime(park.getCloseTime());
            parkDto.setLatitude(park.getLatitude());
            parkDto.setLongitude(park.getLongitude());
            parkDtos.add(parkDto);
        }
        return parkDtos;
    }
}
