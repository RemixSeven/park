package com.ymwang.park.service;

import com.ymwang.park.dto.Wallet.QueryWalletDto;
import com.ymwang.park.dto.Wallet.RechargeDto;
import com.ymwang.park.dto.Wallet.WalletDto;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
public interface WalletService {
    void recharge(RechargeDto rechargeDto);
    WalletDto queryWallet(QueryWalletDto queryWalletDto);
}
