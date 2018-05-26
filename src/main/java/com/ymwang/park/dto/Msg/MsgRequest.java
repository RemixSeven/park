package com.ymwang.park.dto.Msg;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/26
 */
@Data
public class MsgRequest {
    private Integer page;
    private Integer size;
    private String userId;

}
