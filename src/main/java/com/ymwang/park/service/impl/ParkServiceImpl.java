package com.ymwang.park.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isEmpty(addParkDto.getParkName())||StringUtils.isEmpty(addParkDto.getParkAddress())||
                StringUtils.isEmpty(addParkDto.getParkDetail())||StringUtils.isEmpty(addParkDto.getLatitude())||
                StringUtils.isEmpty(addParkDto.getLongitude())||addParkDto.getOpenTime()==null||
                addParkDto.getCloseTime()==null){
            throw new BizException("api.park.empty", "停车场信息都为必填项,请检查未填项");
        }
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
            park.setValid("1");
            parkMapper.insertSelective(park);
        }
    }

    private boolean isParkNameExist(AddParkDto addParkDto) {
        if ((parkMapper.selectByParkName(addParkDto.getParkName()))==null)
        {
            return false;
        }
        return true;
    }

    @Override
    public void editPark(EditParkDto editParkDto) {
        Park park=new Park();
        if (StringUtils.isEmpty(editParkDto.getParkDetail())
                ||editParkDto.getOpenTime()==null||
                editParkDto.getCloseTime()==null){
            throw new BizException("api.park.empty", "修改的内容都为必填项,请检查未填项后再修改");
        }
        park.setParkId(editParkDto.getParkId());
        park.setParkDetail(editParkDto.getParkDetail());
        park.setOpenTime(editParkDto.getOpenTime());
        park.setCloseTime(editParkDto.getCloseTime());
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
           /* if (distance<5000.00) {*/
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
                if(avgScoreParkDto!=null) {
                    queryParkReponse.setAvgScore(avgScoreParkDto.getAvgScore());
                }else {
                    queryParkReponse.setAvgScore(5.00);
                }
                queryParkReponses.add(queryParkReponse);
         /*   }*/
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
            int placeTotal=placeMapper.placeTotal(park.getParkId());
            parkDto.setPlaceTotal(placeTotal);
            int placeSurplus=placeMapper.placeSurplus(park.getParkId());
            parkDto.setPlaceSurplus(placeSurplus);
            AvgScoreParkDto avgScoreParkDto=commentaryMapper.queryAvgScore(park.getParkId());
            if (avgScoreParkDto!=null){
                parkDto.setAvgScore(avgScoreParkDto.getAvgScore());
            }
            parkDtos.add(parkDto);
        }
        return parkDtos;
    }

    @Override
    public AllParkResponse allPark(AllParkDto allParkDto) {
        AllParkResponse allParkResponse=new AllParkResponse();
        List<Park> parkList=parkMapper.queryPark();
        PageHelper.startPage(allParkDto.getPageNum(),allParkDto.getPageSize());
        List<Park> parks=parkMapper.queryPark();
        int sum=parkList.size();
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

        PageInfo<ParkDto> pageInfo=new PageInfo<ParkDto>(parkDtos);
        long total=pageInfo.getTotal();
        allParkResponse.setCount(String.valueOf(total));
        allParkResponse.setParkDtos(parkDtos);
        allParkResponse.setSum(sum);
        return allParkResponse;
    }

    @Override
    public AllParkResponse queryParkByParkName(QueryParkByParkName queryParkByParkName) {
        AllParkResponse allParkResponse=new AllParkResponse();
        List<Park> parkList=parkMapper.queryParkByContent(queryParkByParkName.getParkName());
        PageHelper.startPage(queryParkByParkName.getPageNum(),queryParkByParkName.getPageSize());
        List<Park> parks=parkMapper.queryParkByContent(queryParkByParkName.getParkName());
        int sum=parkList.size();
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

        PageInfo<ParkDto> pageInfo=new PageInfo<ParkDto>(parkDtos);
        long total=pageInfo.getTotal();
        allParkResponse.setCount(String.valueOf(total));
        allParkResponse.setParkDtos(parkDtos);
        allParkResponse.setSum(sum);
        return allParkResponse;
    }

    @Override
    public List<ParkDto> queryParkNearby(QueryParkByContentDto queryParkByContentDto) {
        List<Park> parks=parkMapper.queryParkByContent(queryParkByContentDto.getContent());
        List<ParkDto> parkDtos=new ArrayList<>();
        for (Park park:parks){
            double distance=LocationUtils.getDistance(30.320964,120.351044,Double.parseDouble(park.getLatitude()),Double.parseDouble(park.getLongitude()));
             if (distance<5000.00) {
                 ParkDto parkDto = new ParkDto();
                 parkDto.setParkId(park.getParkId());
                 parkDto.setParkName(park.getParkName());
                 parkDto.setParkDetail(park.getParkDetail());
                 parkDto.setParkAddress(park.getParkAddress());
                 parkDto.setOpenTime(park.getOpenTime());
                 parkDto.setCloseTime(park.getCloseTime());
                 parkDto.setLatitude(park.getLatitude());
                 parkDto.setLongitude(park.getLongitude());
                 int placeTotal = placeMapper.placeTotal(park.getParkId());
                 parkDto.setPlaceTotal(placeTotal);
                 int placeSurplus = placeMapper.placeSurplus(park.getParkId());
                 parkDto.setPlaceSurplus(placeSurplus);
                 AvgScoreParkDto avgScoreParkDto = commentaryMapper.queryAvgScore(park.getParkId());
                 if (avgScoreParkDto != null) {
                     parkDto.setAvgScore(avgScoreParkDto.getAvgScore());
                 }else {
                     parkDto.setAvgScore(5.00);
                 }
                 parkDto.setDistance(distance);
                 parkDtos.add(parkDto);
             }
        }
        Collections.sort(parkDtos,new Comparator<ParkDto>() {

            @Override
            public int compare(ParkDto o1, ParkDto o2) {
                // TODO Auto-generated method stub
                BigDecimal b1 = new BigDecimal(o1.getDistance());
                BigDecimal b2 = new BigDecimal(o2.getDistance());
                return (int) b1.subtract(b2).doubleValue();
            }
        });
        return parkDtos;
    }
}
