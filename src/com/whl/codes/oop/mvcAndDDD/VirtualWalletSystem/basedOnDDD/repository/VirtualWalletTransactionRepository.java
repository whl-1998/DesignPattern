package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.repository;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.entity.VirtualWalletTransactionEntity;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.enums.Status;

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
