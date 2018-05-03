package com.ymwang.park.dto.Car;

import lombok.Data;


/**
 * @Author: wym
 * @Date: 2018/4/28
 */
@Data
public class AddCarDto {
    private String carNumber;

    private String userId;

    private String carName;

    private String carColor;
}
