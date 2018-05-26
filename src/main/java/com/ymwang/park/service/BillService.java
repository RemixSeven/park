package com.ymwang.park.service;


import com.ymwang.park.dto.Bill.BillDto;
import com.ymwang.park.dto.Bill.DeleteBillDto;
import com.ymwang.park.dto.Bill.QueryBillDto;

import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/23
 */
public interface BillService {
    void deleteBill(DeleteBillDto deleteBillDto);
    List<BillDto> queryBill(QueryBillDto queryBillDto);
}
