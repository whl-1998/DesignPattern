package com.whl.oop.abstractClassAndInterface.logSystem;

import java.util.logging.Level;

/**
 * @author whl
 * @version V1.0
 * @Title: 用于输出日志到消息中间件
 * @Description:
 */
public class MessageQueueLogger extends Logger{
    private MessageQueueClient msgQueueClient;

    public MessageQueueLogger(String name, boolean enabled, Level minPermittedLevel, MessageQueueClient msgQueueClient) {
        super(name, enabled, minPermittedLevel);
        this.msgQueueClient = msgQueueClient;
    }

    @Override
    public void doLog(Level level, String message) {
        //TODO：格式化level和message, 输出message到中间件
        msgQueueClient.send(message);
    }
}
