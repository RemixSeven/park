package com.ymwang.park.dto.User;

import lombok.Data;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/26
 */
@Data
public class BatchForbidUserDto {
    private List<ForbidUserDto> forbidUserDtos;
}
