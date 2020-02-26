package com.kaizhuo.component.oss.storage;

import com.kaizhuo.component.oss.config.OssProperties;

import java.io.InputStream;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.oss.storage
 * @description: 文件上传类
 * @author: miaochen
 * @create: 2020-02-25 21:29
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/

public abstract class StorageService {

    OssProperties config;

    /**
     * 获取上传token
     * @param bucketName 空间名
     * @return 上传token
     */
    public abstract String getUploadToken(String bucketName);

    /**
     * 文件上传
     * @param data       文件字节数组
     * @param bucketName 空间名
     * @param path       文件路径
     * @return           返回http地址
     */
    public abstract String upload(byte[] data,String bucketName,String path);

    /**
     * 文件上传
     *
     * @param data       文件字节数组
     * @param bucketName 空间名
     * @param suffix     后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String bucketName, String suffix);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param bucketName  空间名
     * @param path        文件路径，包含文件名
     * @return 返回http地址
     */
    public abstract String upload(InputStream inputStream, String bucketName, String path);

    /**
     * 文件上传
     *
     * @param inputStream 字节流
     * @param bucketName  空间名
     * @param suffix      后缀
     * @return 返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String bucketName, String suffix);
}
