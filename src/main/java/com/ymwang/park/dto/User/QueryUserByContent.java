package com.ymwang.park.dto.User;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/23
 */
@Data
public class QueryUserByContent {
    private int pageNum;
    private int pageSize;
    private String content;
}
