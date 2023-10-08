package com.zhilianhua.ssicp.entity.app;

/**
 * 消息类型枚举。主要用于路由
 */
public enum MessageTypeEnum {
    ALARM("alarm"),
//    MACHINE_INSPECTION_ERROR("machineInspectionError"),
//    MANUAL_INSPECTION_ERROR("manualInspectionError"),
    MANUAL_INSPECTION("manualInspection"),
    MACHINE_INSPECTION("machineInspection"),
    SYSTEM("system"),
    ;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    MessageTypeEnum(String type) {
        this.type = type;
    }
}
