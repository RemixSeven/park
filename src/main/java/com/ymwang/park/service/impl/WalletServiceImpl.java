package com.ymwang.park.service.impl;

import com.ymwang.park.dao.BillMapper;
import com.ymwang.park.dao.WalletMapper;
import com.ymwang.park.dto.Wallet.QueryWalletDto;
import com.ymwang.park.dto.Wallet.RechargeDto;
import com.ymwang.park.dto.Wallet.WalletDto;
import com.ymwang.park.model.Bill;
import com.ymwang.park.model.Wallet;
import com.ymwang.park.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletMapper walletMapper;
    @Autowired
    BillMapper billMapper;
    @Override
    public void recharge(RechargeDto rechargeDto) {
        Wallet wallet=walletMapper.selectByPrimaryKey(rechargeDto.getWalletId());
        int balance=wallet.getBalance()+rechargeDto.getBalance();
        wallet.setBalance(balance);
        walletMapper.updateByPrimaryKeySelective(wallet);
        Bill bill=new Bill();
        bill.setBillId(UUID.randomUUID().toString().replaceAll("-", ""));
        bill.setConsume(rechargeDto.getBalance());
        bill.setUserId(rechargeDto.getUserId());
        bill.setIsDelete("0");
        bill.setType("0");
        billMapper.insertSelective(bill);
    }

    @Override
    public WalletDto queryWallet(QueryWalletDto queryWalletDto) {
        WalletDto walletDto=new WalletDto();
        Wallet wallet=walletMapper.queryWallet(queryWalletDto.getUserId());
        walletDto.setWalletId(wallet.getWalletId());
        walletDto.setUserId(wallet.getUserId());
        walletDto.setBalance(wallet.getBalance());
        return walletDto;
    }
}
