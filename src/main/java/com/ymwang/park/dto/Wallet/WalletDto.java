package com.ymwang.park.dto.Wallet;

import lombok.Data;

/**
 * @Author: wym
 * @Date: 2018/5/8
 */
@Data
public class WalletDto {
    private String walletId;

    private String userId;

    private Integer balance;
}
