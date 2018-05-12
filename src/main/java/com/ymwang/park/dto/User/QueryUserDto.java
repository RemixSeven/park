package com.ymwang.park.dto.User;

import lombok.Data;

import java.util.List;


/**
 * @Author: wym
 * @Date: 2018/5/11
 */
@Data
public class QueryUserDto {
    private List<UserDto> userDtos;
    private String count;
}
