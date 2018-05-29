package com.ymwang.park.dto.Place;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/29
 */
@Data
public class ParkStatus {
    private String status;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date  enterTime;
}
