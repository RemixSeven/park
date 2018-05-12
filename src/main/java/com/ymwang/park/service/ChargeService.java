package com.ymwang.park.service;

import com.ymwang.park.dto.Charge.AddChargeDto;
import com.ymwang.park.dto.Charge.QueryChargeRequest;
import com.ymwang.park.dto.Charge.QueryCharyDto;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/4/30
 */
public interface ChargeService {
    void addCharge(AddChargeDto addChargeDto);
    List<QueryCharyDto> queryCharge(QueryChargeRequest queryChargeRequest);
}
