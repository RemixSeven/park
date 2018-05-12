package com.ymwang.park.dto.User;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/11
 */
@Data
public class QueryUserRequest {
    private int pageNum;
    private int pageSize;
}
