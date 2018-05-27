package com.ymwang.park.controller;

import com.ymwang.park.dto.Wallet.QueryWalletDto;
import com.ymwang.park.dto.Wallet.RechargeDto;
import com.ymwang.park.dto.Wallet.WalletDto;
import com.ymwang.park.service.WalletService;
import com.ymwang.park.utils.ResultMessage;
import com.ymwang.park.utils.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@RestController
@RequestMapping(value = "/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;
    @RequestMapping(method = RequestMethod.POST,value = "/recharge")
    public SingleResult<String> recharge(@RequestBody RechargeDto rechargeDto){
        String result=walletService.recharge(rechargeDto);
        SingleResult<String> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(result);
        return response;
    }
    @RequestMapping(method = RequestMethod.POST,value = "/queryWallet")
    public SingleResult<WalletDto> queryWallet(@RequestBody QueryWalletDto queryWalletDto){
        WalletDto walletDto=walletService.queryWallet(queryWalletDto);
        SingleResult<WalletDto> response = new SingleResult(ResultMessage.SUCCESS);
        response.setData(walletDto);
        return response;
    }
}
