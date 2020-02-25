package com.kaizhuo.core.model.domain.enums;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: miaochen
 * \* @Date: 2020/2/25
 * \* @Time: 14:54
 * \* To change this template use File | Settings | File Templates.
 * \* @Description: 状态枚举
 * \
 */
public enum YnEnums {
    N(0, "无效"),
    Y(1, "有效");

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

    YnEnums(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
