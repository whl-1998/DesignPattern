package com.whl.codes.designPatterns.creation.singleton.id_generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whl
 * @version V1.0
 * @Title: 支持lazy-load, 双重检测
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
