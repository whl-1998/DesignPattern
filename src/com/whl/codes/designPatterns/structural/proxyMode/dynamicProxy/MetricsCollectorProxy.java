package com.whl.codes.designPatterns.structural.proxyMode.dynamicProxy;

import com.whl.codes.designPatterns.structural.proxyMode.MetricsCollector;
import com.whl.codes.designPatterns.structural.proxyMode.RequestInfo;
import com.whl.codes.designPatterns.structural.proxyMode.UserController;
import com.whl.codes.designPatterns.structural.proxyMode.IUserController;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author whl
 * @version V1.0
 * @Title: 动态代理类
 * @Description:
 */
public class MetricsCollectorProxy {
    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    //传入参数为被代理类实例
    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();//被代理类接口
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);//创建代理类执行器
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxiedObject;//被代理类

        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        /**
         * 增强逻辑
         * @param proxy 代理类
         * @param method 方法
         * @param args 方法参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long startTimestamp = System.currentTimeMillis();
            Object result = method.invoke(proxiedObject, args);//方法返回值
            long endTimeStamp = System.currentTimeMillis();
            long responseTime = endTimeStamp - startTimestamp;
            String apiName = proxiedObject.getClass().getName() + ":" + method.getName();
            metricsCollector.recordRequest(new RequestInfo(apiName, responseTime, startTimestamp));
            return result;
        }
    }

    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) proxy.createProxy(new UserController());
        userController.login("155", "whl");
    }
}
