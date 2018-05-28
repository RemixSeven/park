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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Override
    public void addCharge(AddChargeDto addChargeDto) {
        int fee=addChargeDto.getMoney();
        if (!"".equals(addChargeDto.getCouponId())){
            Coupon coupon=couponMapper.selectByPrimaryKey(addChargeDto.getCouponId());
            CouponDeploy couponDeploy=couponDeployMapper.selectById(coupon.getCouponId());
            if (couponDeploy.getKaquanid()==1){
                fee=fee-2;
            }else {
                fee=fee-5;
            }
        }
        Wallet wallet=walletMapper.queryWallet(addChargeDto.getUserId());
        if (wallet.getBalance()-fee<0){
            throw new BizException("api.charge.not.sufficient.funds","扣款失败，余额不足，请充值之后再付款");
        }
        Coupon coupon=couponMapper.selectByPrimaryKey(addChargeDto.getCouponId());
        coupon.setStatus(1);
        couponMapper.updateByPrimaryKeySelective(coupon);
        Charge charge=new Charge();
        charge.setChargeId(UUID.randomUUID().toString().replaceAll("-", ""));
        charge.setCarNumber(addChargeDto.getCarNumber());
        charge.setEnterTime(addChargeDto.getEnterTime());
        charge.setOutTime(addChargeDto.getOutTime());
        charge.setUserName(addChargeDto.getUserName());
        charge.setCarNumber(addChargeDto.getCarNumber());
        charge.setParkId(addChargeDto.getParkId());
        charge.setUserId(addChargeDto.getUserId());
        charge.setMoney(fee);
        charge.setValid("1");
        chargeMapper.insert(charge);
        Place place=placeMapper.selectByPrimaryKey(addChargeDto.getPId());
        place.setInuserId(null);
        placeMapper.updateByPrimaryKey(place);
        wallet.setBalance(wallet.getBalance()-fee);
        walletMapper.updateByPrimaryKeySelective(wallet);
        Bill bill=new Bill();
        bill.setBillId(UUID.randomUUID().toString().replaceAll("-", ""));
        bill.setType("1");
        bill.setIsDelete("0");
        bill.setUserId(addChargeDto.getUserId());
        bill.setConsume(fee);
        billMapper.insert(bill);
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
