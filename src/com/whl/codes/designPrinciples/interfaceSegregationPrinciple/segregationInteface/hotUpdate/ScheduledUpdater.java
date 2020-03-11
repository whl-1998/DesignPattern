package com.whl.designPrinciples.interfaceSegregationPrinciple.segregationInteface.hotUpdate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author whl
 * @version V1.0
 * @Title: 执行热更新
 * @Description:
 */
public class ScheduledUpdater {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private long initialDelayInSeconds;//初始化延迟时间
    private long periodInSeconds;//执行周期
    private Updater updater;

    public ScheduledUpdater(Updater updater, long initialDelayInSeconds, long periodInSeconds) {
        this.updater = updater;
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
    }

    public void run() {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //交给线程池以固定频率执行热更新操作
                updater.hotUpdate();
            }
        }, this.initialDelayInSeconds, this.periodInSeconds, TimeUnit.SECONDS);
    }
}
