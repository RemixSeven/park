package com.ymwang.park.dto.Place;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
@Data
public class ReservePlaceDto {
    private String userId;
    private String pId;
    private int pNum;
    private String parkId;
}
