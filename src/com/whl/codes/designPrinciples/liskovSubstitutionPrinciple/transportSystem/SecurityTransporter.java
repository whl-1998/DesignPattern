package com.whl.designPrinciples.liskovSubstitutionPrinciple.transportSystem;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import sun.net.www.http.HttpClient;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class SecurityTransporter extends Transporter{
    private String appId;
    private String appToken;

    public SecurityTransporter(HttpClient client, String appId, String appToken) {
        super(client);
        this.appId = appId;
        this.appToken = appToken;
    }

    @Override
    public HttpResponse sendRequest(HttpRequest request) {
        if (!appId.equals("") && !appToken.equals("")) {
            //TODO: request请求体中添加appId、appToken
        }
        return super.sendRequest(request);
    }
}
