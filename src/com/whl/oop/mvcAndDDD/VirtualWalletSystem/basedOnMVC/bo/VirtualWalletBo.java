package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.bo;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class VirtualWalletBo {
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
