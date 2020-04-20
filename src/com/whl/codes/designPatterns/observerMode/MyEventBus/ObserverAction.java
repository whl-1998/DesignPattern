package com.whl.codes.designPatterns.observerMode.MyEventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class ObserverAction {
    private Object target;//观察者实例
    private Method method;//对应的@Subscribe方法

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event) { // event 是 method 方法的参数
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
