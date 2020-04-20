package com.whl.codes.designPatterns.observerMode.MyEventBus;

import java.util.concurrent.Executor;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */

public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
