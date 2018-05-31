package com.ymwang.park.service.impl;

import com.ymwang.park.dao.BillMapper;
import com.ymwang.park.dao.CouponDeployMapper;
import com.ymwang.park.dao.CouponMapper;
import com.ymwang.park.dao.WalletMapper;
import com.ymwang.park.dto.Wallet.QueryWalletDto;
import com.ymwang.park.dto.Wallet.RechargeDto;
import com.ymwang.park.dto.Wallet.WalletDto;
import com.ymwang.park.model.Bill;
import com.ymwang.park.model.Coupon;
import com.ymwang.park.model.CouponDeploy;
import com.ymwang.park.model.Wallet;
import com.ymwang.park.service.WalletService;
import com.ymwang.park.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    @Autowired
    CouponDeployMapper couponDeployMapper;
    @Autowired
    CouponMapper couponMapper;
    @Override
    public String recharge(RechargeDto rechargeDto) {
        String response=null;
        Wallet wallet=walletMapper.selectByPrimaryKey(rechargeDto.getWalletId());
        int balance=wallet.getBalance()+rechargeDto.getBalance();
        wallet.setBalance(balance);
        walletMapper.updateByPrimaryKeySelective(wallet);
        if (balance>=100){
            HashMap map=new HashMap();
            map.put("date",DateUtils.getDate());
            map.put("couponId",2);
            CouponDeploy couponDeploy=couponDeployMapper.queryCouponByCommentary(map);
            if (couponDeploy!=null){
                Coupon coupon=new Coupon();
                coupon.setCouponId(couponDeploy.getId());
                coupon.setUserId(rechargeDto.getUserId());
                coupon.setStatus(0);
                couponMapper.insertSelective(coupon);
                response="充值成功，谢谢您的支持，我们已赠送一张5元优惠券，请往卡券包查收";
            }
        }
        Bill bill=new Bill();
        bill.setBillId(UUID.randomUUID().toString().replaceAll("-", ""));
        bill.setConsume(rechargeDto.getBalance());
        bill.setUserId(rechargeDto.getUserId());
        bill.setIsDelete("0");
        bill.setType("0");
        billMapper.insertSelective(bill);
        return response;
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
