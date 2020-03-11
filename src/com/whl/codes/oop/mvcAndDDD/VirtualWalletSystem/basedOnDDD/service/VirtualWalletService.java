package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.service;

import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.bo.VirtualWalletBo;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.entity.VirtualWalletEntity;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.entity.VirtualWalletTransactionEntity;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.enums.Status;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.exception.NoSufficientBalanceException;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.repository.VirtualWalletRepository;
import com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.repository.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * @author whl
 * @version V1.0
 * @Title: MVC与DDD的核心区别, 轻service重BO
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
        //获取到walletEntity实例
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        //将walletEntity实例填充为VirtualWallet实例
        VirtualWalletBo wallet = convert(walletEntity);
        //调用VirtualWallet实例的debit()方法执行入账逻辑
        wallet.debit(amount);
        //更新数据库中对应wallet的余额
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void credit(Long walletId, BigDecimal amount) {
        //获取到walletEntity实例
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        //注意这里与MVC模型的区别
        //将walletEntity实例填充为VirtualWallet实例
        VirtualWalletBo wallet = convert(walletEntity);
        //调用VirtualWallet实例的credit()方法执行转账逻辑
        wallet.credit(amount);
        //更新数据库中对应wallet的余额
        walletRepo.updateBalance(walletId, wallet.balance());
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
