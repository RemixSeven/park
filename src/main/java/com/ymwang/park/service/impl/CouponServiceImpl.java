package com.ymwang.park.service.impl;

import com.ymwang.park.dao.CouponDeployMapper;
import com.ymwang.park.dao.CouponMapper;
import com.ymwang.park.dto.Coupon.QueryCoupon;
import com.ymwang.park.dto.Coupon.UserCouponDto;
import com.ymwang.park.model.Coupon;
import com.ymwang.park.model.CouponDeploy;
import com.ymwang.park.model.CouponDeployKey;
import com.ymwang.park.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponDeployMapper couponDeployMapper;
    @Override
    public List<UserCouponDto> queryCoupon(QueryCoupon queryCoupon) {
        List<UserCouponDto> userCouponDtos=new ArrayList<>();
        List<Coupon> coupons=couponMapper.queryCoupon(queryCoupon.getUserId());
        for (Coupon coupon:coupons){
            CouponDeploy couponDeploy=couponDeployMapper.selectById(coupon.getCouponId());
            UserCouponDto userCouponDto=new UserCouponDto();
            userCouponDto.setId(coupon.getId());
            userCouponDto.setEffectTime(couponDeploy.getEffiEndTime());
            userCouponDto.setStatus(coupon.getStatus());
            if (couponDeploy.getKaquanid()==1){
                userCouponDto.setName("2元停车优惠券");
            }else {
                userCouponDto.setName("5元停车优惠券");
            }
            userCouponDtos.add(userCouponDto);
        }
        return userCouponDtos;
    }
}
