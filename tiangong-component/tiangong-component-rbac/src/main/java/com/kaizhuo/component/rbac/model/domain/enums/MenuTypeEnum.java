package com.kaizhuo.component.rbac.model.domain.enums;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModel;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.model.domain.enums
 * @description: 目录类型
 * @author: miaochen
 * @create: 2020-02-25 22:26
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ApiModel("目录类型")
public enum MenuTypeEnum {

    MENU(1, "菜单"),
    COMPONENT(2, "页面组件");

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

    public void setName(String value) {
        this.name = value;
    }

    MenuTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static String getNameByKey(Integer key) {
        for (MenuTypeEnum item : values()) {
            if (item.key == key) {
                return item.name;
            }
        }
        return null;
    }

    public static JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for (MenuTypeEnum e : values()) {
            JSONObject item = new JSONObject();
            item.put("key", e.getKey());
            item.put("name", e.getName());
            array.add(item);
        }
        return array;
    }
}
