package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.repository;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.entity.VirtualWalletEntity;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title: 虚拟钱包repo
 * @Description:
 */
public interface VirtualWalletRepository {
    VirtualWalletEntity getWalletEntity(Long walletId);

    BigDecimal getBalance(Long walletId);

    void updateBalance(Long walletId, BigDecimal subtract);
}
