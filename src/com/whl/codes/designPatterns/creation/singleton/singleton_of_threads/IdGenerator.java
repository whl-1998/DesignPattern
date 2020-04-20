package com.whl.codes.designPatterns.creation.singleton.singleton_of_threads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author whl
 * @version V1.0
 * @Title: 线程间单例
 * @Description:
 */
public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);

    private static final ConcurrentHashMap<Long, IdGenerator> instances = new ConcurrentHashMap<>();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        Long currentThreadId = Thread.currentThread().getId();
        //如果当前线程Id在Map中并不存在实例, 则创建
        instances.putIfAbsent(currentThreadId, new IdGenerator());
        //返回创建后的实例
        return instances.get(currentThreadId);
    }

    public long getId() {
        return id.incrementAndGet();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(IdGenerator.getInstance() == IdGenerator.getInstance());
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println(IdGenerator.getInstance() == IdGenerator.getInstance());
        });
        t2.start();
    }
}
