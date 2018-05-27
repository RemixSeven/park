package com.ymwang.park.dto.Park;

import lombok.Data;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/27
 */
@Data
public class AllParkResponse {
    private List<ParkDto> parkDtos;
    private String count;
}
