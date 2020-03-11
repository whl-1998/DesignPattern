package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.exception;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class NoSufficientBalanceException extends RuntimeException{
    public NoSufficientBalanceException(String msg) {
        super(msg);
    }
}
