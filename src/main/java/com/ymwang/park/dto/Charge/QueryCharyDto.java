package com.ymwang.park.dto.Charge;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/11
 */
@Data
public class QueryCharyDto {
    private String userId;

    private String parkId;
    private String parkName;

    private String userName;

    private Integer money;

    private String carNumber;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date enterTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date outTime;
}
