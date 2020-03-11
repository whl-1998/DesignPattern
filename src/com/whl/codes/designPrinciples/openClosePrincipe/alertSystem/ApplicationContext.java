package com.whl.designPrinciples.openClosePrincipe.alertSystem;

import com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler.AlertRule;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler.ErrorAlertHandler;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler.TimeoutTpsAlertHandler;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.alertHandler.TpsAlertHandler;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.fromApiSystem.ApiStatInfo;
import com.whl.designPrinciples.openClosePrincipe.alertSystem.notification.Notification;

/**
 * @author whl
 * @version V1.0
 * @Title: 对外提供的功能接口类
 * @Description:
 */
public class ApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;//执行器
    // 饿汉式单例
    private static final ApplicationContext instance = new ApplicationContext();

    //获取alert实例
    public Alert getAlert() {
        return alert;
    }

    //私有化构造器
    private ApplicationContext() {
        //创建告警规则及告警渠道
        alertRule = new AlertRule();
        notification = new Notification();
        //创建alert实例并添加handlers
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        alert.addAlertHandler(new TimeoutTpsAlertHandler(alertRule, notification));
    }

    //公有获取单例的方法
    public static ApplicationContext getInstance() {
        return instance;
    }

    //使用示例
    public static void main(String[] args) {
        //创建ApplicationContext实例, 执行初始化工作并获取执行器
        ApplicationContext ac = ApplicationContext.getInstance();
        //模拟外部获取的ApiStatInfo实例
        //ApiStatInfo实例包含了接口, 以及接口当前的一些状态值
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        //对apiStatInfo实例进行告警校验
        ac.getAlert().check(apiStatInfo);
    }
}
