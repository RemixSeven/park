package com.ymwang.park.dto.Charge;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enterTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date outTime;
    private String pId;
}
