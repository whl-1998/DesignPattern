package com.whl.codes.designPatterns.observerMode.MyEventBus;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */

public class EventBus {
    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

    public EventBus() {
//        this(MoreExecutors.directExecutor());
        //TODO: 初始化单线程EventBus实例
    }

    protected EventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchedObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.execute(event);
                }
            });
        }
    }
}
