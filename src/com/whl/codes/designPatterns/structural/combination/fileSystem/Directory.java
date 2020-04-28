package com.whl.designPatterns.combinationMode.fileSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 目录类
 * @Description:
 */
public class Directory extends FileSystemNode{
    private List<FileSystemNode> subNodes;

    public Directory(String path) {
        super(path, false);
        subNodes = new ArrayList<>();
    }

    @Override
    public int countNumOfFiles() {
        int res = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            //如果fileOrDir是文件类, 那么res += 1
            //如果fileOrDir是目录类, 那么递归继续计数
            res += fileOrDir.countNumOfFiles();
        }
        return res;
    }

    @Override
    public long countSizeOfFiles() {
        long sizeOfFiles = 0;
        for (FileSystemNode fileOrDir : subNodes) {
            //如果fileOrDir是文件类, 那么sizeOfFiles += fileOrDir文件的大小
            //如果fileOrDir是目录类, 那么递归继续计数
            sizeOfFiles += countSizeOfFiles();
        }
        return sizeOfFiles;
    }

    public void addSubNode(FileSystemNode fileOrDir) {
        subNodes.add(fileOrDir);
    }

    public List<String> getChildFilePaths() {
        List<String> res = new ArrayList<>();
        java.io.File file = new java.io.File(path);
        for (File f : file.listFiles()) {
            res.add(f.getPath());
        }
        return res;
    }
}
