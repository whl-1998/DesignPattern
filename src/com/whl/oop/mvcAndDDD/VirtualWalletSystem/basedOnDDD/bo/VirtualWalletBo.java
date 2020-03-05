package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.bo;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.exception.InvalidAmountException;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.exception.NoSufficientBalanceException;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class VirtualWalletBo {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;
    private boolean isAllowedOverdraft = true;//默认允许透支
    private BigDecimal overdraftAmount = BigDecimal.ZERO;//透支金额默认为0
    private BigDecimal frozenAmount = BigDecimal.ZERO;//冻结金额默认为0

    public VirtualWalletBo(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) {
        //TODO: 冻结指定金额
    }

    public void unfreeze(BigDecimal amount) {
        //TODO: 解冻指定金额
    }
    public void increaseOverdraftAmount(BigDecimal amount) {
        //TODO: 增加指定透支金额
    }
    public void decreaseOverdraftAmount(BigDecimal amount) {
        //TODO: 减少指定透支金额
    }
    public void closeOverdraft() {
        //TODO: 关闭透支
    }
    public void openOverdraft() {
        //TODO: 打开透支
    }

    //出账
    public void debit(BigDecimal amount) {
        //获取到最大可用金额
        BigDecimal totalAvailableBalance = getAvailableBalance();
        //参数合法校验
        if (totalAvailableBalance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException("最大可用金额不足, 无法出账");
        }
        //余额减去相应值
        this.balance.subtract(amount);
    }

    //获取最大可用金额 (余额 + 透支金额)
    private BigDecimal getAvailableBalance() {
        //减去冻结的金额
        BigDecimal totalAvailableBalance = this.balance.subtract(this.frozenAmount);
        //若允许透支, 那么加上透支的金额
        if (isAllowedOverdraft) {
            totalAvailableBalance.add(this.overdraftAmount);
        }
        return totalAvailableBalance;
    }

    //入账
    public void credit(BigDecimal amount) {
        //参数合法校验
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("amount 不合法");
        }
        //当前实例余额增加相应的值
        this.balance.add(amount);
    }

    //获取余额
    public BigDecimal balance() {
        return balance;
    }

    public Long getId() {
        return id;
    }

    public Long getCreateTime() {
        return createTime;
    }
}
