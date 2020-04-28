package com.whl.codes.designPatterns.creation.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whl
 * @version V1.0
 * @Title: 静态内部类
 * @Description:
 */
public class IdGeneratorInnerClz {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static IdGeneratorInnerClz instance;

    private IdGeneratorInnerClz() {
    }

    private static class SingletonHolder {
        private static final IdGeneratorInnerClz instance = new IdGeneratorInnerClz();
    }

    public static IdGeneratorInnerClz getInstance() {
        return instance;
    }

    public long getId() {
        return atomicInteger.incrementAndGet();
    }
}
