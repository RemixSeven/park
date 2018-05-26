package com.ymwang.park.dto.Bill;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Author: wym
 * @Date: 2018/5/25
 */
@Data
public class BillDto {
    private String billId;

    private Integer consume;

    private String type;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
