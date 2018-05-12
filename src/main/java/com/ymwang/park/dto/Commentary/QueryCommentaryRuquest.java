package com.ymwang.park.dto.Commentary;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Data
public class QueryCommentaryRuquest {
    private String parkId;
    private int pageNum;
    private int pageSize;
}
