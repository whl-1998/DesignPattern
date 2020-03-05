package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.entity;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title: 虚拟钱包实体类
 * @Description:
 */
public class VirtualWalletEntity {
    private Long id;
    private Long createTime;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
