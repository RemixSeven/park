package com.ymwang.park.dto.Commentary;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Data
public class CommentaryDto {
    private String cId;
    private String userId;
    private String parkId;
    private String parkName;
    private String userName;
    private String name;
    private String cDetail;
}
