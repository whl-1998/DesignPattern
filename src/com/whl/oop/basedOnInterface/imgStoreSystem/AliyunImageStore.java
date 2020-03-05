package com.whl.oop.basedOnInterface.imgStoreSystem;

import java.awt.*;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class AliyunImageStore implements ImageStore {
    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExist(bucketName);
        String accessToken = generateAccessToken();
        //TODO：上传逻辑...
        return null;//返回图片在阿里云上的url
    }

    @Override
    public Image download(String url) {
        String accessToken = generateAccessToken();
        //TODO：获取到下载的图片流并返回
        return null;
    }

    private String generateAccessToken() {
        //TODO：获取accessToken
        return null;
    }

    private void createBucketIfNotExist(String bucketName) {
        //TODO
    }
}
