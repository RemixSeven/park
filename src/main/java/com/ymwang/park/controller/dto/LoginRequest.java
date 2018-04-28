package com.ymwang.park.controller.dto;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/4/27
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
