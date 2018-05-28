package com.ymwang.park.dto.Park;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/28
 */
@Data
public class EditParkDto {
    private String parkId;
    private String parkDetail;
    @JSONField(format="HH:mm:ss")
    private Date openTime;
    @JSONField(format="HH:mm:ss")
    private Date closeTime;
}
