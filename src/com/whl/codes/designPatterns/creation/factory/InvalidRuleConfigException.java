package com.whl.codes.designPatterns.creation.factory;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description: 自定义异常类
 */
public class InvalidRuleConfigException extends RuntimeException {
    public InvalidRuleConfigException(String s) {
        super(s);
    }
}
