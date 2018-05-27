package com.ymwang.park.dto.Charge;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/25
 */
@Data
public class QueryDailyIncomeRequest {
    private String parkId;
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    private int pageNum;
    private int pageSize;
}
