package com.zhilianhua.ssicp.entity.aimodel.enums;

import com.alibaba.fastjson2.annotation.JSONField;

// 管段类型
public enum PipeTypeEnum {

    新钢管("新钢管", 0),
    旧钢管("旧钢管", 1),
    ;
    private String name;

    @JSONField(value = true)
    private int code;

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

    PipeTypeEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
