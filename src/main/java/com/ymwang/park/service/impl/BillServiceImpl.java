package com.ymwang.park.service.impl;

import com.ymwang.park.dao.BillMapper;
import com.ymwang.park.dto.Bill.BillDto;
import com.ymwang.park.dto.Bill.DeleteBillDto;
import com.ymwang.park.dto.Bill.QueryBillDto;
import com.ymwang.park.model.Bill;
import com.ymwang.park.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wym
 * @Date: 2018/5/23
 */
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;
    @Override
    public void deleteBill(DeleteBillDto deleteBillDto) {
        Bill bill=billMapper.selectByPrimaryKey(deleteBillDto.getBillId());
        bill.setIsDelete("1");
        billMapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public List<BillDto> queryBill(QueryBillDto queryBillDto) {
        List<BillDto> billDtos=new ArrayList<>();
        List<Bill> bills=billMapper.queryBill(queryBillDto.getUserId());
        for (Bill bill:bills){
            BillDto billDto=new BillDto();
            billDto.setBillId(bill.getBillId());
            billDto.setType(bill.getType());
            billDto.setConsume(bill.getConsume());
            billDto.setCreateTime(bill.getCreateTime());
            billDtos.add(billDto);
        }
        return billDtos;
    }
}
