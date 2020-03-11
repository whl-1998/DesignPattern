package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.service;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.bo.VirtualWalletBo;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.entity.VirtualWalletEntity;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.entity.VirtualWalletTransactionEntity;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.enums.Status;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.exception.NoSufficientBalanceException;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.repository.VirtualWalletRepository;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnMVC.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title: MVC与DDD的核心区别, 业务逻辑全部放在Service中
 * @Description:
 */
public class VirtualWalletService {
    //依赖注入
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        //根据传入id获取到walletEntity实例
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        //对walletEntity实例填充逻辑, 转换为walletBo
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        //TODO: 填充walletEntity逻辑
        return null;
    }

    public BigDecimal getBalance(Long walletId) {
        //根据传入id获取到对应虚拟钱包的余额值
        return walletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) {
        //根据传入id获取到walletEntity实例
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        //获取walletEntity实例的余额值
        BigDecimal balance = walletEntity.getBalance();
        //出账参数合法校验
        if (balance.compareTo(amount) < 0) {
            throw new NoSufficientBalanceException("...");
        }
        //更新walletEntity实例的余额值
        walletRepo.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) {
        //根据传入id获取到walletEntity实例
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        //获取到walletEntity实例的余额
        BigDecimal balance = walletEntity.getBalance();
        //更新walletEntity实例的余额值
        walletRepo.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        //创建交易流水实例
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        //初始化流水实例的Status字段为"等待被执行"
        transactionEntity.setStatus(Status.TO_BE_EXECUTED);
        //持久化流水实例到数据库
        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            //执行出账操作
            debit(fromWalletId, amount);
            //执行入账操作
            credit(toWalletId, amount);
        } catch (NoSufficientBalanceException e) {
            transactionRepo.updateStatus(transactionId, Status.CLOSED);
            //TODO：将异常信息写入日志
        } catch (Exception e) {
            transactionRepo.updateStatus(transactionId, Status.FAILED);
            //TODO：将异常信息写入日志
        }
        //当转账操作完整执行后, 更新交易流水实例的Status字段为"成功执行"
        transactionRepo.updateStatus(transactionId, Status.EXECUTED);
    }
}
