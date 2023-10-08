package com.zhilianhua.ssicp.entity.app;

/**
 * 消息统计类型枚举，主要用于统计
 */
public enum MessageAnalyseTypeEnum {
    ALARM("alarm"),
    INSPECTION("inspection"),
    ERROR("error"),
    SYSTEM("system"),
    ;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    MessageAnalyseTypeEnum(String type) {
        this.type = type;
    }
}
