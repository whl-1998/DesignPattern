package com.whl.oop.apiRPCsystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 提供给外部调用的功能接口
 * @Description:
 */
public interface ApiAuthenticator {
    //根据传入url进行鉴权
    void auth(String url);

    //根据传入的apiRequest进行鉴权
    void auth(ApiRequest apiRequest);
}
