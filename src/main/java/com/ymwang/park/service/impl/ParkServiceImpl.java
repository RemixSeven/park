package com.ymwang.park.service.impl;

import com.ymwang.park.dao.ChargeStrategyMapper;
import com.ymwang.park.dao.CommentaryMapper;
import com.ymwang.park.dao.PlaceMapper;
import com.ymwang.park.dto.ChargeStrategy.ChargeStrategyDto;
import com.ymwang.park.dto.Commentary.AvgScoreParkDto;
import com.ymwang.park.dto.Park.*;
import com.ymwang.park.dao.ParkMapper;
import com.ymwang.park.model.ChargeStrategy;
import com.ymwang.park.model.Park;
import com.ymwang.park.model.Place;
import com.ymwang.park.service.ParkService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.LocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
@Service
public class ParkServiceImpl implements ParkService {
    @Autowired
    ParkMapper parkMapper;
    @Autowired
    PlaceMapper placeMapper;
    @Autowired
    ChargeStrategyMapper chargeStrategyMapper;
    @Autowired
    CommentaryMapper commentaryMapper;

    @Override
    public void addPark(AddParkDto addParkDto) {
        if (isParkNameExist(addParkDto)) {
            throw new BizException("api.parkName.exist", "停车场名称不能重复");
        }else {
            Park park = new Park();
            park.setParkId(UUID.randomUUID().toString().replaceAll("-", ""));
            park.setParkName(addParkDto.getParkName());
            park.setParkAddress(addParkDto.getParkAddress());
            park.setParkDetail(addParkDto.getParkDetail());
            park.setOpenTime(addParkDto.getOpenTime());
            park.setCloseTime(addParkDto.getCloseTime());
            park.setLatitude(addParkDto.getLatitude());
            park.setLongitude(addParkDto.getLongitude());
            parkMapper.insertSelective(park);
        }
    }

    private boolean isParkNameExist(AddParkDto addParkDto) {
        if ((parkMapper.queryParkByContent(addParkDto.getParkName()))==null)
        {
            return false;
        }
        return true;
    }

    @Override
    public void editPark(ParkDto parkDto) {
        Park park=new Park();
        park.setParkId(parkDto.getParkId());
        park.setParkName(parkDto.getParkName());
        park.setParkAddress(parkDto.getParkAddress());
        park.setParkDetail(parkDto.getParkDetail());
        park.setOpenTime(parkDto.getOpenTime());
        park.setCloseTime(parkDto.getCloseTime());
        park.setLatitude(parkDto.getLatitude());
        park.setLongitude(parkDto.getLongitude());
        parkMapper.updateByPrimaryKeySelective(park);
    }

    @Override
    public void deletePark(DeleteParkDto deleteParkDto) {
        Park park=parkMapper.selectByPrimaryKey(deleteParkDto.getParkId());
        park.setValid("0");
        parkMapper.updateByPrimaryKeySelective(park);
    }

    @Override
    public List<QueryParkReponse> queryPark(QueryParkDto queryParkDto) {
        List<QueryParkReponse> queryParkReponses=new ArrayList<>();
        List<Park> parks=parkMapper.queryPark();
        for (Park park:parks){
            double distance=LocationUtils.getDistance(Double.parseDouble(queryParkDto.getLatitude()),Double.parseDouble(queryParkDto.getLongitude()),Double.parseDouble(park.getLatitude()),Double.parseDouble(park.getLongitude()));
            if (distance<5000.00) {
                QueryParkReponse queryParkReponse = new QueryParkReponse();
                queryParkReponse.setParkId(park.getParkId());
                queryParkReponse.setParkName(park.getParkName());
                queryParkReponse.setParkAddress(park.getParkAddress());
                queryParkReponse.setParkDetail(park.getParkDetail());
                queryParkReponse.setOpenTime(park.getOpenTime());
                queryParkReponse.setCloseTime(park.getCloseTime());
                queryParkReponse.setLatitude(park.getLatitude());
                queryParkReponse.setLongitude(park.getLongitude());
                queryParkReponse.setDistance(distance);
                int placeTotal=placeMapper.placeTotal(park.getParkId());
                queryParkReponse.setPlaceTotal(placeTotal);
                int placeSurplus=placeMapper.placeSurplus(park.getParkId());
                ChargeStrategy chargeStrategy=chargeStrategyMapper.queryChargeStrategy(park.getParkId());
                int oneHour=chargeStrategy.getOneHour();
                queryParkReponse.setOneHour(oneHour);
                queryParkReponse.setPlaceSurplus(placeSurplus);
                AvgScoreParkDto avgScoreParkDto=commentaryMapper.queryAvgScore(park.getParkId());
                queryParkReponse.setAvgScore(avgScoreParkDto.getAvgScore());
                queryParkReponses.add(queryParkReponse);
            }
        }
        Collections.sort(queryParkReponses,new Comparator<QueryParkReponse>() {

            @Override
            public int compare(QueryParkReponse o1, QueryParkReponse o2) {
                // TODO Auto-generated method stub
                BigDecimal b1 = new BigDecimal(o1.getDistance());
                BigDecimal b2 = new BigDecimal(o2.getDistance());
                return (int) b1.subtract(b2).doubleValue();
            }
        });
        return queryParkReponses;
    }

    @Override
    public List<ParkDto> queryParkByContent(QueryParkByContentDto queryParkByContentDto) {
        List<Park> parks=parkMapper.queryParkByContent(queryParkByContentDto.getContent());
        List<ParkDto> parkDtos=new ArrayList<>();
        for (Park park:parks){
            ParkDto parkDto=new ParkDto();
            parkDto.setParkId(park.getParkId());
            parkDto.setParkName(park.getParkName());
            parkDto.setParkDetail(park.getParkDetail());
            parkDto.setParkAddress(park.getParkAddress());
            parkDto.setOpenTime(park.getOpenTime());
            parkDto.setCloseTime(park.getCloseTime());
            parkDto.setLatitude(park.getLatitude());
            parkDto.setLongitude(park.getLongitude());
            parkDtos.add(parkDto);
        }
        return parkDtos;
    }
}
