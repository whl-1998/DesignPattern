package com.whl.oop.apiRPCsystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 默认的接口请求校验, 默认通过mysql存储数据
 * @Description:
 */
public class DefaultApiAuthenticatorImpl implements ApiAuthenticator{
    private CredentialStorage credentialStorage;

    //默认采用mysql存储
    public DefaultApiAuthenticatorImpl() {
        this.credentialStorage = new MysqlCredentialStorage();
    }

    public DefaultApiAuthenticatorImpl(CredentialStorage credentialStorage) {
        this.credentialStorage = credentialStorage;
    }

    //根据传入url进行鉴权
    @Override
    public void auth(String url) {
        //通过url构建apiRequest实例
        auth(new ApiRequest(url));
    }

    //根据传入apiRequest实例鉴权
    @Override
    public void auth(ApiRequest apiRequest) {
        String appId = apiRequest.getAppId();//获取url中的appId
        String token = apiRequest.getToken();//获取url中的token
        long timestamp = apiRequest.getTimeStamp();//获取url中的时间戳
        String originalUrl = apiRequest.getOriginalUrl();//获取url中的原始url头

        //构建客户端AuthToken
        AuthToken clientAuthToken = new AuthToken(token, timestamp);//根据token、时间戳构建clientAuthToken
        //若clientAuthToken失效, 抛出异常
        if (clientAuthToken.isExpired()) {
            throw new RuntimeException("Token is expired.");
        }
        //根据appId从存储获取到password
        String password = credentialStorage.getPasswordByAppId(appId);
        //通过url、appId、password、时间戳构建token
        AuthToken serverAuthToken = AuthToken.generate(originalUrl, appId, password, timestamp);
        //若两个token不匹配, 抛出异常
        if (!serverAuthToken.match(clientAuthToken)) {
            throw new RuntimeException("Token verification failed.");
        }
    }
}
