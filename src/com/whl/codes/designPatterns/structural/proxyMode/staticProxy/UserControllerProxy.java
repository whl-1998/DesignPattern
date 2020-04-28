package com.whl.codes.designPatterns.structural.proxyMode.staticProxy;

import com.whl.codes.designPatterns.structural.proxyMode.*;

/**
 * @author whl
 * @version V1.0
 * @Title: 静态代理类
 * @Description:
 */
public class UserControllerProxy implements IUserController {
    private MetricsCollector metricsCollector;
    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.metricsCollector = new MetricsCollector();
        this.userController = userController;
    }

    @Override
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = userController.login(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        metricsCollector.recordRequest(new RequestInfo("login", responseTime, startTimestamp));
        return userVo;
    }

    @Override
    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        UserVo userVo = userController.register(telephone, password);
        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        metricsCollector.recordRequest(new RequestInfo("register", responseTime, startTimestamp));
        return userVo;
    }

    public static void main(String[] args) {
        IUserController userController = new UserControllerProxy(new UserController());
        userController.login("155", "whl");
    }
}
