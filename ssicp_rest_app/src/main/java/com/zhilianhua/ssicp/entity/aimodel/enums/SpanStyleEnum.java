package com.zhilianhua.ssicp.entity.aimodel.enums;

import com.alibaba.fastjson2.annotation.JSONField;

// 敷设方式
public enum SpanStyleEnum {

    室外架空("室外架空", 0),
    室内架空("室内架空", 1),
    通行地沟("通行地沟", 2),
    不通行地沟("不通行地沟", 3),
    无沟直埋("无沟直埋", 4),
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

    SpanStyleEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
