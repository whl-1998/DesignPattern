package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface;

import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.outsideParts.ConfigSource;
import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.viewConfigInfoByWeb.Viewer;

import java.util.Map;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class MysqlConfig implements Viewer {
    private ConfigSource configSource; //配置中心（比如zookeeper）
    private String address;
    private int timeout;
    private int maxTotal;
    //省略其他配置: maxWaitMillis,maxIdle,minIdle...

    //通过配置中心实例初始化
    public MysqlConfig(ConfigSource configSource) {
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
    public String outputInPlainText() {
        //TODO: 输出配置信息到文本
        return null;
    }

    @Override
    public Map<String, String> output() {
        //TODO: 以key - value的形式输出配置信息, key = 参数名称, value = 参数值
        return null;
    }
}
