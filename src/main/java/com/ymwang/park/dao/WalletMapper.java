package com.ymwang.park.dao;

import com.ymwang.park.model.Wallet;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletMapper {
    int deleteByPrimaryKey(String walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(String walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);
    Wallet queryWallet(String userId);
}