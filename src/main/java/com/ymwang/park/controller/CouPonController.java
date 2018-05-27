package com.ymwang.park.controller;

import com.ymwang.park.dao.CouponDeployMapper;
import com.ymwang.park.dto.Coupon.CouponDeployDto;
import com.ymwang.park.dto.Coupon.QueryCoupon;
import com.ymwang.park.dto.Coupon.UserCouponDto;
import com.ymwang.park.model.CouponDeploy;
import com.ymwang.park.service.CouponService;
import com.ymwang.park.utils.DateUtils;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@RestController
@RequestMapping("/coupon")
public class CouPonController {
    @Autowired
    CouponDeployMapper couponDeployMapper;
    @Autowired
    CouponService couponService;
    @RequestMapping(value = "/deployCoupon",method = RequestMethod.POST)
    public SingleResult<String> deployKq(@RequestBody CouponDeployDto couponDeployDto){
        String [] couponid=couponDeployDto.getCouponids().split(",");
        for(String id:couponid){
            CouponDeploy couponDeploy=new CouponDeploy();
            couponDeploy.setKaquanid(Integer.valueOf(id));
            couponDeploy.setActiviStartTime(couponDeployDto.getActivi_start_time());
            couponDeploy.setActiviEndTime(couponDeployDto.getActivi_end_time());
            couponDeploy.setEffiStartTime(couponDeployDto.getEffi_start_time());
            couponDeploy.setEffiEndTime(couponDeployDto.getEffi_end_time());
            couponDeployMapper.insertSelective(couponDeploy);
        }
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(null);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryCoupon")
    public SingleResult<List<UserCouponDto>> queryCoupon(@RequestBody QueryCoupon queryCarDto){
        List<UserCouponDto> userCouponDtos=couponService.queryCoupon(queryCarDto);
        SingleResult<List<UserCouponDto>> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(userCouponDtos);
        return response;
    }
}
