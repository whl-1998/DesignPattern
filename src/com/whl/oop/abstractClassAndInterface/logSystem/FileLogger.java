package com.whl.oop.abstractClassAndInterface.logSystem;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;

/**
 * @author whl
 * @version V1.0
 * @Title: 用于输出日志到文件系统
 * @Description:
 */
public class FileLogger extends Logger {
    private Writer fileWriter;

    public FileLogger(String name, boolean enabled, Level minPermittedLevel, String filePath) throws IOException {
        super(name, enabled, minPermittedLevel);
        this.fileWriter = new FileWriter(filePath);
    }

    @Override
    public void doLog(Level level, String message) {
        //TODO：格式化level和message, 输出message到日志文件
        try {
            fileWriter.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
