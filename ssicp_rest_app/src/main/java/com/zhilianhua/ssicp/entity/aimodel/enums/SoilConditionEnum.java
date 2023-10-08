package com.zhilianhua.ssicp.entity.aimodel.enums;


import com.alibaba.fastjson2.annotation.JSONField;

// 土壤干湿情况
public enum SoilConditionEnum {
    干土壤("干土壤", 1),
    不太湿土壤("不太湿土壤", 2),
    较湿土壤("较湿土壤", 3),
    很湿土壤("很湿土壤", 4);
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

    SoilConditionEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
