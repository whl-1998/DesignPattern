package com.whl.designPrinciples.liskovSubstitutionPrinciple.transportSystem;


import com.sun.deploy.net.HttpRequest;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class Demo {
    public void demoFunction(Transporter transporter, HttpRequest request) {
        transporter.sendRequest(request);
    }
}
