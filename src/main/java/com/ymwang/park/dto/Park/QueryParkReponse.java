package com.ymwang.park.dto.Park;

import com.alibaba.fastjson.annotation.JSONField;
import com.ymwang.park.dto.ChargeStrategy.ChargeStrategyDto;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/6
 */
@Data
public class QueryParkReponse {
    private String parkId;
    private String parkName;

    private String parkAddress;

    private String parkDetail;
    @JSONField(format="HH:mm:ss")
    private Date openTime;
    @JSONField(format="HH:mm:ss")
    private Date closeTime;

    private String longitude;

    private String latitude;
    private double distance;
    /*    车位总数*/
    private int placeTotal;
    /*    车位剩余数*/
    private int placeSurplus;
/*    private ChargeStrategyDto chargeStrategyDto;*/
    private int OneHour;
}
