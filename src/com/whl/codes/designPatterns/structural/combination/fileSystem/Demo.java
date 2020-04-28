package com.whl.designPatterns.combinationMode.fileSystem;


import java.util.List;

/**
 * @author whl
 * @version V1.0
 * @Title: 测试类
 * @Description:
 */
public class Demo {
    private String sourcePath;

    public Demo(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void buildOrganization() {
        Directory dir = new Directory(sourcePath);
        buildOrganization(dir);
    }

    private void buildOrganization(Directory dir) {
        //获取dir下的所有子文件、子目录的路径
        List<String> childFilePaths = dir.getChildFilePaths();
        //如果是文件, 直接add到subNodes; 如果是目录, 则递归add到subNodes
        for (String s : childFilePaths) {
            if (new java.io.File(s).isDirectory()) {
                Directory directory = new Directory(s);
                dir.addSubNode(directory);
                buildOrganization(directory);
            } else {
                dir.addSubNode(new File(s));
            }
        }
    }

    public int countNumOfFiles(FileSystemNode dirOrFile) {
        return dirOrFile.countNumOfFiles();
    }

    public long countSizeOfFiles(FileSystemNode dirOrFile) {
        return dirOrFile.countSizeOfFiles();
    }
}
