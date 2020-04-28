package com.whl.codes.designPatterns.creation.singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whl
 * @version V1.0
 * @Title: 饿汉式单例
 * @Description:
 */
public class IdGenerator {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static final IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public long getId() {
        return atomicInteger.incrementAndGet();
    }
}