package com.ymwang.park.service.impl;

import com.ymwang.park.dao.WalletMapper;
import com.ymwang.park.dto.Wallet.QueryWalletDto;
import com.ymwang.park.dto.Wallet.RechargeDto;
import com.ymwang.park.dto.Wallet.WalletDto;
import com.ymwang.park.model.Wallet;
import com.ymwang.park.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletMapper walletMapper;
    @Override
    public void recharge(RechargeDto rechargeDto) {
        Wallet wallet=walletMapper.selectByPrimaryKey(rechargeDto.getWalletId());
        int balance=wallet.getBalance()+rechargeDto.getBalance();
        wallet.setBalance(balance);
        walletMapper.updateByPrimaryKeySelective(wallet);
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
