package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.repository;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.enums.Status;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.entity.VirtualWalletTransactionEntity;

/**
 * @author whl
 * @version V1.0
 * @Title: 交易流水repo
 * @Description:
 */
public interface VirtualWalletTransactionRepository {
    Long saveTransaction(VirtualWalletTransactionEntity transactionEntity);

    void updateStatus(Long transactionId, Status closed);
}
