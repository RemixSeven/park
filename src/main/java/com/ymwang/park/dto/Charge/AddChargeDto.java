package com.ymwang.park.dto.Charge;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Data
public class AddChargeDto {
    private String userId;

    private String parkId;

    private String userName;

    private Integer money;

    private String carNumber;

    private Date enterTime;

    private Date outTime;
}
