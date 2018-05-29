package com.ymwang.park.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymwang.park.dao.*;
import com.ymwang.park.dto.Charge.*;
import com.ymwang.park.dto.Park.AllParkDto;
import com.ymwang.park.model.*;

import com.ymwang.park.service.ChargeService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ymwang.park.utils.DateUtils.getDate;
import static com.ymwang.park.utils.DateUtils.getDistanceOfTwoDate;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Service
public class ChargeServiceImpl implements ChargeService {
    @Autowired
    ChargeMapper chargeMapper;
    @Autowired
    PlaceMapper placeMapper;
    @Autowired
    WalletMapper walletMapper;
    @Autowired
    ParkMapper parkMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponDeployMapper couponDeployMapper;
    @Autowired
    ChargeStrategyMapper chargeStrategyMapper;
    @Override
    public ChargeDto addCharge(AddChargeDto addChargeDto) {
        Charge charge=chargeMapper.parkCharge(addChargeDto.getUserId());
        Date date=DateUtils.parseDate(getDate("yyyy-MM-dd HH:mm:ss"));
        long disTime=getDistanceOfTwoDate(charge.getEnterTime(),date);
        ChargeStrategy chargeStrategy=chargeStrategyMapper.queryChargeStrategy(charge.getParkId());
        int fee=0;
        if (disTime<=1){
            fee=chargeStrategy.getOneHour();
        }else if (disTime<=3){
            fee=chargeStrategy.getOneHour();
        }else if (disTime<=5){
            fee=chargeStrategy.getFiveHour();
        }else {
            fee=chargeStrategy.getCapping();
        }
        if (addChargeDto.getCouponId()!=0)
        {
            Coupon coupon=couponMapper.selectByPrimaryKey(Integer.valueOf(addChargeDto.getCouponId()));
            CouponDeploy couponDeploy=couponDeployMapper.selectById(coupon.getCouponId());
            if (couponDeploy.getKaquanid()==1){
                fee=fee-2;
            }else {
                fee=fee-5;
            }
        }
        Wallet wallet=walletMapper.queryWallet(charge.getUserId());
        if (wallet.getBalance()-fee<0){
            throw new BizException("api.charge.not.sufficient.funds","扣款失败，余额不足，请充值之后再付款");
        }
        if (addChargeDto.getCouponId()!=0) {
            Coupon coupon = couponMapper.selectByPrimaryKey(addChargeDto.getCouponId());
            coupon.setStatus(1);
            couponMapper.updateByPrimaryKeySelective(coupon);
        }
        charge.setChargeId(charge.getChargeId());
        charge.setOutTime(date);
        charge.setMoney(fee);
        charge.setValid("1");
        chargeMapper.updateByPrimaryKeySelective(charge);
        Place place=placeMapper.inusePlace(charge.getUserId());
        place.setInuserId(null);
        placeMapper.updateByPrimaryKey(place);
        wallet.setBalance(wallet.getBalance()-fee);
        walletMapper.updateByPrimaryKeySelective(wallet);
        Bill bill=new Bill();
        bill.setBillId(UUID.randomUUID().toString().replaceAll("-", ""));
        bill.setType("1");
        bill.setIsDelete("0");
        bill.setUserId(charge.getUserId());
        bill.setConsume(fee);
        billMapper.insert(bill);
        ChargeDto chargeDto=new ChargeDto();
        chargeDto.setMoney(fee);
        return chargeDto;
    }

    @Override
    public List<QueryCharyDto> queryCharge(QueryChargeRequest queryChargeRequest) {
        List<QueryCharyDto> queryCharyDtos = new ArrayList<>();
        List<Charge> charges = chargeMapper.queryCharge(queryChargeRequest.getUserId());
        for (Charge charge : charges) {
            QueryCharyDto queryCharyDto = new QueryCharyDto();
            queryCharyDto.setUserId(charge.getUserId());
            queryCharyDto.setParkId(charge.getParkId());
            Park park = parkMapper.selectByPrimaryKey(charge.getParkId());
            queryCharyDto.setParkName(park.getParkName());
            queryCharyDto.setCarNumber(charge.getCarNumber());
            queryCharyDto.setMoney(charge.getMoney());
            queryCharyDto.setUserName(charge.getUserName());
            queryCharyDto.setEnterTime(charge.getEnterTime());
            queryCharyDto.setOutTime(charge.getOutTime());
            queryCharyDtos.add(queryCharyDto);
        }
        return queryCharyDtos;
    }

    @Override
    public DailyIncomeResponse queryDailyIncome(QueryDailyIncomeRequest queryDailyIncomeRequest) {
        DailyIncomeResponse dailyIncomeResponse=new DailyIncomeResponse();
        PageHelper.startPage(queryDailyIncomeRequest.getPageNum(),queryDailyIncomeRequest.getPageSize());
        HashMap map=new HashMap();
        map.put("parkId",queryDailyIncomeRequest.getParkId());
        map.put("startDate",DateUtils.formatDate(queryDailyIncomeRequest.getStartDate()));
        map.put("endDate",DateUtils.formatDate(queryDailyIncomeRequest.getEndDate()));
        List<DailyIncomeDto> incomes=chargeMapper.queryDailyIncome(map);
        List<DailyIncomeDto> incomeDtoList=chargeMapper.queryDailyIncome(map);
        int sum=incomeDtoList.size();
        PageInfo<DailyIncomeDto> pageInfo=new PageInfo<DailyIncomeDto>(incomes);
        long total=pageInfo.getTotal();
        dailyIncomeResponse.setCount(String.valueOf(total));
        dailyIncomeResponse.setDailyIncomeDtos(incomes);
        dailyIncomeResponse.setSum(sum);
        return dailyIncomeResponse;
    }

    @Override
    public List<ParkIncome> queryParkIncome(QueryParkIncomeRequest queryParkIncomeRequest) {
        HashMap map=new HashMap();
        map.put("month",DateUtils.formatYearMonth(queryParkIncomeRequest.getMonth()));
        List<ParkIncomeByParkId> parkIncomeByParkIds=chargeMapper.queryParkIncome(map);
        List<ParkIncome> parkIncomes=new ArrayList<>();
        for (ParkIncomeByParkId parkIncomeByParkId:parkIncomeByParkIds){
            ParkIncome parkIncome=new ParkIncome();
            Park park=parkMapper.selectByPrimaryKey(parkIncomeByParkId.getParkId());
            parkIncome.setParkName(park.getParkName());
            parkIncome.setMoney(parkIncomeByParkId.getMoney());
            parkIncomes.add(parkIncome);
        }
        return parkIncomes;
    }
    @Override
    public DailyIncomeResponse allParkDailyIncome(AllParkDto allParkDto) {
               DailyIncomeResponse dailyIncomeResponse=new DailyIncomeResponse();
               PageHelper.startPage(allParkDto.getPageNum(),allParkDto.getPageSize());
               List<Park> parks=parkMapper.queryPark();
               Park park=parks.get(0);
               HashMap map=new HashMap();
               map.put("parkId",park.getParkId());
               List<DailyIncomeDto> incomes=chargeMapper.allParkDailyIncome(map);
               List<DailyIncomeDto> incomeDtoList=chargeMapper.allParkDailyIncome(map);
               int sum=incomeDtoList.size();
               PageInfo<DailyIncomeDto> pageInfo=new PageInfo<DailyIncomeDto>(incomes);
               long total=pageInfo.getTotal();
                dailyIncomeResponse.setCount(String.valueOf(total));
               dailyIncomeResponse.setDailyIncomeDtos(incomes);
               dailyIncomeResponse.setSum(sum);
               return dailyIncomeResponse;
           }
}
