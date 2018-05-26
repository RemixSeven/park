package com.ymwang.park.dto.Charge;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/25
 */
@Data
public class DailyIncomeDto {
    @JSONField(format = "yyyy-MM-dd")
    private Date parkDate;
    private int num;
    private int money;
}
