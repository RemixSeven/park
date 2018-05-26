package com.ymwang.park.dto.Charge;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/25
 */
@Data
public class QueryParkIncomeRequest {
    @JSONField(format = "yyyy-MM")
    private Date month;
}
