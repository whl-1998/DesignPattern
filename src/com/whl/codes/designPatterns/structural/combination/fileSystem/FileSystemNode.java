package com.whl.designPatterns.combinationMode.fileSystem;

/**
 * @author whl
 * @version V1.0
 * @Title: 父类模版类
 * @Description:
 */
public abstract class FileSystemNode {
    protected String path;
    protected boolean isFile;

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}
