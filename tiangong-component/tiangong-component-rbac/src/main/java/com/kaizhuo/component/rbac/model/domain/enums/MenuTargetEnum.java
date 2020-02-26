package com.kaizhuo.component.rbac.model.domain.enums;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModel;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.domain.enums
 * @description: 目录打开
 * @author: miaochen
 * @create: 2020-02-25 22:24
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("目录打开")
public enum MenuTargetEnum {
    LAYOUT(1, "当前页"),
    PARENT(2, "当前页面顶部"),
    BLANK(3, "重新打开");

    private Integer key;
    private String name;

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

    MenuTargetEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }
    public static String getNameByKey(Integer key) {
        for (MenuTargetEnum item : values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (MenuTargetEnum e :values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
