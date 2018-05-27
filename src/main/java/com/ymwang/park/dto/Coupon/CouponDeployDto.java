package com.ymwang.park.dto.Coupon;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@Data
public class CouponDeployDto {
    @JSONField(format = "yyyy-MM-dd")
    private Date activi_start_time;
    @JSONField(format = "yyyy-MM-dd")
    private Date activi_end_time;
    @JSONField(format = "yyyy-MM-dd")
    private Date effi_start_time;
    @JSONField(format = "yyyy-MM-dd")
    private Date effi_end_time;
    private String  couponids;
}
