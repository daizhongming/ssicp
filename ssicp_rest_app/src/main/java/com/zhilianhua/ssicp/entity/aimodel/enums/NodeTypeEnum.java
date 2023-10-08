package com.zhilianhua.ssicp.entity.aimodel.enums;

import com.alibaba.fastjson2.annotation.JSONField;

// 节点类型
public enum NodeTypeEnum {
    GAS("NTGas", 1),//气源
    USER("NTUser", 2),//用户
    ELBOW("NTElbow", 3),//弯头
    SANTONG("NTSantong", 4),//三通
    JOIN("NTJoin", 5),//大小头
    POINT("NTPoint", 6),//虚拟仪表
    VALVE("NTValve", 7),//阀门
    COMPENSATOR("NTCompensator", 8),//补偿器
    ;
    private String name;

    @JSONField(value = true)
    private int code;

    NodeTypeEnum(String name, int code) {
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
