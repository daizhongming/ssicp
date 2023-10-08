package com.zhilianhua.ssicp.entity.aimodel.enums;

import com.alibaba.fastjson2.annotation.JSONField;

// 介质类型
public enum MediumTypeEnum {
    GAS("MediumGas", 1), WATER("MediumWater", 2), OTL("MediumOtL", 3), OTG("MediumOtG", 4),
    ;
    private String name;

    @JSONField(value = true)
    private int code;

    MediumTypeEnum(String name, int code) {
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
