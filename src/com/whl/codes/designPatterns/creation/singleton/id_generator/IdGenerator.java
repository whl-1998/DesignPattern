package com.whl.codes.designPatterns.creation.singleton.id_generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author whl
 * @version V1.0
 * @Title:
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