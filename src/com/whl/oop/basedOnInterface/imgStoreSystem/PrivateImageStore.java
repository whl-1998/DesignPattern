package com.whl.oop.basedOnInterface.imgStoreSystem;

import java.awt.*;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class PrivateImageStore implements ImageStore {
    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExist(bucketName);
        //TODO：上传图片并返回图片地址
        return null;
    }

    @Override
    public Image download(String url) {
        //TODO：从私有云上下载图片并返回
        return null;
    }

    private void createBucketIfNotExist(String bucketName) {
        //TODO
    }
}
