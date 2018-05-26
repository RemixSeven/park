package com.ymwang.park.dao;

import com.ymwang.park.model.Bill;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillMapper {
    int deleteByPrimaryKey(String billId);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(String billId);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
    List<Bill> queryBill(String userId);
}