package com.ymwang.park.service;

import com.ymwang.park.dto.Coupon.QueryCoupon;
import com.ymwang.park.dto.Coupon.UserCouponDto;
import com.ymwang.park.model.CouponDeploy;

import java.util.List;
import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
public interface CouponService {
    List<UserCouponDto> queryCoupon(QueryCoupon queryCoupon);
    List<CouponDeploy> queryAllDeployCoupon();
}
