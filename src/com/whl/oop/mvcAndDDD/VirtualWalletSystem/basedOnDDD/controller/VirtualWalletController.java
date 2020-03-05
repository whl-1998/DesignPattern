package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.controller;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.service.VirtualWalletService;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title: 虚拟钱包Controller
 * @Description:
 */
public class VirtualWalletController {
    private VirtualWalletService virtualWalletService;//依赖注入

    public BigDecimal getBalance(Long walletId) {
        return virtualWalletService.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        virtualWalletService.debit(walletId, amount);
    }

    public void credit(Long walletId, BigDecimal amount) {
        virtualWalletService.credit(walletId, amount);
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        virtualWalletService.transfer(fromWalletId, toWalletId, amount);
    }
}
