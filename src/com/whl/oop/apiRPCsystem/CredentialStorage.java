package com.whl.oop.apiRPCsystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 模拟从存储获取数据
 * @Description:
 */
public interface CredentialStorage {
    String getPasswordByAppId(String appId);
}
