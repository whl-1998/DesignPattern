package com.whl.codes.designPatterns.observerMode.MyEventBus;

import java.lang.reflect.Method;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class Preconditions {
    public static void checkArgument(boolean b, String s, Method method, int length) {
        //todo：校验参数是否合法
    }

    public static Object checkNotNull(Object target) {
        //todo: 校验参数是否为null
        return target;
    }
}
