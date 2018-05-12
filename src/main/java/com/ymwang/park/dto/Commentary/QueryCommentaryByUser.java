package com.ymwang.park.dto.Commentary;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/11
 */
@Data
public class QueryCommentaryByUser {
    private String userId;
    private int pageNum;
    private int pageSize;
}
