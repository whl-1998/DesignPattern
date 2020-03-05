package com.whl.oop.basedOnInterface.imgStoreSystem;

import java.awt.*;

public interface ImageStore {
    //上传图片
    String upload(Image image, String bucketName);

    //下载图片
    Image download(String url);
}
