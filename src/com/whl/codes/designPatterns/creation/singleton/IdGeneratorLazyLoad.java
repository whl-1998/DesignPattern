package com.whl.codes.designPatterns.creation.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whl
 * @version V1.0
 * @Title: 懒汉式单例
 * @Description:
 */
public class IdGeneratorLazyLoad {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static IdGeneratorLazyLoad instance;

    private IdGeneratorLazyLoad() {
    }

    public synchronized static IdGeneratorLazyLoad getInstance() {
        if (instance == null) {
            instance = new IdGeneratorLazyLoad();
        }
        return instance;
    }

    public long getId() {
        return atomicInteger.incrementAndGet();
    }
}
