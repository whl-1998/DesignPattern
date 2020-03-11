package com.whl.oop.abstractClassAndInterface.logSystem;


import java.util.logging.Level;

/**
 * @author whl
 * @version V1.0
 * @Title: 抽象类示例：日志系统
 * @Description:
 */
public abstract class Logger {
    private String name;
    private boolean enabled;
    private Level minPermittedLevel;

    public Logger(String name, boolean enabled, Level minPermittedLevel) {
        this.name = name;
        this.enabled = enabled;
        this.minPermittedLevel = minPermittedLevel;
    }

    public void log(Level level, String message) {
        boolean loggable = enabled && (minPermittedLevel.intValue() <= level.intValue());
        if (!loggable) {
            return;
        }
        doLog(level, message);
    }

    public abstract void doLog(Level level, String message);
}
