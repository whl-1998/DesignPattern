package com.whl.codes.designPatterns.creation.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public enum IdGeneratorEnum {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        IdGeneratorEnum.INSTANCE.getId();
    }
}

