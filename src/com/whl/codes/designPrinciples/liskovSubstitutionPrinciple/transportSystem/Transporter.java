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
public class Transporter {
    private HttpClient httpClient;

    public Transporter(HttpClient client) {
        this.httpClient = client;
    }

    public HttpResponse sendRequest(HttpRequest request) {
        //TODO: use httpClient to send request
        return null;
    }
}
