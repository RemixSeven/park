package com.ymwang.park.controller.dto;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/4/27
 */
@Data
public class UserRequest {
    private String userId;

    private String userName;
    private String password;

    private String userType;

    private String phone;

    private String name;
    private String code;
}
