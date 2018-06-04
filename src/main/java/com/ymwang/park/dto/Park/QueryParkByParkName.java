package com.ymwang.park.dto.Park;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/6/2
 */
@Data
public class QueryParkByParkName {
    private int pageNum;
    private int pageSize;
    private String parkName;
}
