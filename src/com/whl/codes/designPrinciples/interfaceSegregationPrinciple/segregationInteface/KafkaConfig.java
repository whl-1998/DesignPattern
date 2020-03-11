package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface;

import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.hotUpdate.Updater;
import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.outsideParts.ConfigSource;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class KafkaConfig implements Updater {
    private ConfigSource configSource; //配置中心（比如zookeeper）
    private String address;
    private int timeout;
    private int maxTotal;
    //省略其他配置: maxWaitMillis,maxIdle,minIdle...

    //通过配置中心实例初始化
    public KafkaConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    //将配置中心实例的信息配置到address、timeout、maxTotal等属性上
    public void updateInfo() {
        //TODO: 将配置中心实例的信息配置到address、timeout、maxTotal等属性上
    }

    public String getAddress() {
        return address;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    @Override
    public void hotUpdate() {
        //TODO: 以固定频率更新配置信息
    }
}
