package com.whl.oop.basedOnInterface.imgStoreSystem;

import java.awt.*;

/**
 * @author whl
 * @version V1.0
 * @Title: 对外提供的功能接口
 * @Description:
 */
public class Application {
    private String bucketName;

    public Application() {
        this.bucketName = "ai_images_bucket";
    }

    public Application(String bucketName) {
        this.bucketName = bucketName;
    }

    public String processUpload(Image image, ImageStore imgStore) {
        return imgStore.upload(image, bucketName);
    }

    public Image processDownload(String url, ImageStore imgStore) {
        return imgStore.download(url);
    }
}
