package com.whl.oop.mvcAndDDD.VirtualWalletSystem.basedOnDDD.exception;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String msg) {
        super(msg);
    }
}
