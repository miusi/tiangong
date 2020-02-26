package com.kaizhuo.component.oss.enums;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.oss.enums
 * @description:
 * @author: miaochen
 * @create: 2020-02-25 20:24
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public enum OSSEnum {
    QIUNIU(1, "七牛"),

    ALIYUN(2, "阿里云"),

    TENCENT(3, "腾讯云"),

    UPYUN(4, "又拍云");

    private Integer key;
    private String value;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OSSEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
