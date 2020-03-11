package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface;

import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.hotUpdate.ScheduledUpdater;
import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.outsideParts.ConfigSource;
import com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.viewConfigInfoByWeb.SimpleHttpServer;

/**
 * @author whl
 * @version V1.0
 * @Title: 执行器
 * @Description: 只需要实现redisConfig、kafkaConfig的热更新
 */
public class Application {
    private static final ConfigSource configSource = new ConfigSource();//模拟获取配置中心实例
    private static final RedisConfig redisConfig = new RedisConfig(configSource);
    private static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);
    private static final MysqlConfig mysqlConfig = new MysqlConfig(configSource);

    public static void main(String[] args) {
        //执行热更新redis配置
        ScheduledUpdater redisConfigUpdater = new ScheduledUpdater(redisConfig, 300, 300);
        redisConfigUpdater.run();
        //执行热更新kafka配置
        ScheduledUpdater kafkaConfigUpdater = new ScheduledUpdater(kafkaConfig, 60, 60);
        kafkaConfigUpdater.run();

        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1", 2389);
        simpleHttpServer.addViewers("/config", redisConfig);
        simpleHttpServer.addViewers("/config", mysqlConfig);
        simpleHttpServer.run();
    }
}
