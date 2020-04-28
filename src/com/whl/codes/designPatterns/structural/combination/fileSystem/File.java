package com.whl.designPatterns.combinationMode.fileSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 文件类
 * @Description:
 */
public class File extends FileSystemNode{
    public File(String path) {
        super(path, true);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        return file.exists() ? file.length() : 0;
    }
}
