package com.ymwang.park.dto.Coupon;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@Data
public class UserCouponDto {
    private Integer id;
    private String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date  effectTime;
    private int status;
}
