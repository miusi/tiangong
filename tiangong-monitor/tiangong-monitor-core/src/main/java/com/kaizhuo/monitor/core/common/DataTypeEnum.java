package com.kaizhuo.monitor.core.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.common
 * @description: 数据类型
 * @author: miaochen
 * @create: 2020-02-28 21:31
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public enum DataTypeEnum {
    SERVER(1, "服务器"),
    JVM(2, "JVM"),
    JVM_START(3, "JVM启动"),
    METRIC(4, "自定义数据"),
    METHOD(5, "方法"),
    ALARM(6, "告警");

    private Integer key;
    private String name;

    DataTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (DataTypeEnum item : DataTypeEnum.values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static DataTypeEnum getEnumByKey(Integer key) {
        for (DataTypeEnum item : DataTypeEnum.values()) {
            if (item.key == key) {
                return item;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (DataTypeEnum e : DataTypeEnum.values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
