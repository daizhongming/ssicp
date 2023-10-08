package com.zhilianhua.ssicp.entity.aimodel.enums;

import com.alibaba.fastjson2.annotation.JSONField;

// 天气枚举
public enum WeatherEnum {
    晴天("晴天", 4),
    小雨("小雨", 1),
    中雨("中雨", 2),
    大雨("大雨", 3);

    private String name;

    @JSONField(value = true)
    private int code;

    WeatherEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
