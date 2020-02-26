package com.kaizhuo.component.oss.storage;

import com.aliyun.oss.OSSClient;
import com.kaizhuo.component.oss.config.OssProperties;

import java.io.InputStream;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.oss.storage
 * @description: 阿里云实现
 * @author: miaochen
 * @create: 2020-02-25 21:45
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class AliyunStorgaeService extends StorageService {
    private OSSClient client;

    public AliyunStorgaeService(OssProperties config) {
        this.config = config;
    }

    @Override
    public String getUploadToken(String bucketName) {
        return null;
    }

    @Override
    public String upload(byte[] data, String bucketName, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(byte[] data, String bucketName, String suffix) {
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String bucketName, String path) {
        return null;
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String bucketName, String suffix) {
        return null;
    }
}
