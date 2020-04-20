package com.whl.codes.designPatterns.creation.singleton.logger_example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class Logger {
    private FileWriter writer;
    private static final Logger instance = new Logger();//初始化Logger实例

    private Logger() {//构造器私有化
        try {
            writer = new FileWriter(new File(".../log.txt"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //外部通过getInstance获取到同一个Logger实例
    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) throws IOException {
        writer.write(message);
    }
}

