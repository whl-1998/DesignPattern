package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.entity;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.enums.Status;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title: 交易流水实体类
 * @Description:
 */
public class VirtualWalletTransactionEntity {
    private BigDecimal amount;
    private Long createTime;
    private Long fromWalletId;
    private Long toWalletId;
    private Status status;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getFromWalletId() {
        return fromWalletId;
    }

    public void setFromWalletId(Long fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public Long getToWalletId() {
        return toWalletId;
    }

    public void setToWalletId(Long toWalletId) {
        this.toWalletId = toWalletId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
