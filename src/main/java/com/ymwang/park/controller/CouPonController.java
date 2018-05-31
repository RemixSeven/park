package com.ymwang.park.controller;

import com.ymwang.park.dao.CouponDeployMapper;
import com.ymwang.park.dto.Coupon.CouponDeployDto;
import com.ymwang.park.dto.Coupon.QueryCoupon;
import com.ymwang.park.dto.Coupon.UserCouponDto;
import com.ymwang.park.model.CouponDeploy;
import com.ymwang.park.service.CouponService;
import com.ymwang.park.utils.BizException;
import com.ymwang.park.utils.DateUtils;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    private boolean checkDeployDate(Date startDate, Date endDate){
        List<CouponDeploy> dateList = couponService.queryAllDeployCoupon();
        for(CouponDeploy deploy : dateList){
            if(startDate.compareTo(deploy.getActiviStartTime())>=0
                    && startDate.compareTo(deploy.getActiviEndTime())<=0){
                return false;
            }
            if(endDate.compareTo(deploy.getActiviStartTime())>=0
                    && endDate.compareTo(deploy.getActiviEndTime())<=0){
                return false;
            }
        }
        return true;
    }


    @RequestMapping(value = "/deployCoupon",method = RequestMethod.POST)
    public SingleResult<String> deployKq(@RequestBody CouponDeployDto couponDeployDto){
        String [] couponid=couponDeployDto.getCouponids().split(",");
        if(!checkDeployDate(couponDeployDto.getActivi_start_time(),couponDeployDto.getActivi_end_time())){
            throw new BizException("api.deployCoupon.exist","该段时间内,已经有优惠券活动，请等待活动结束再发布新的活动");
        }
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
