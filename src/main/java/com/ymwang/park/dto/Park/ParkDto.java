package com.ymwang.park.dto.Park;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
@Data
public class ParkDto {
    private String parkId;
    private String parkName;

    private String parkAddress;

    private String parkDetail;

    private Date openTime;

    private Date closeTime;

    private String longitude;

    private String latitude;
}
