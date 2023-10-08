package com.zhilianhua.ssicp.entity.app;

public enum TodoTypeEnum {
    ALARM("alarm"),
    MACHINE_INSPECTION_ERROR("machineInspectionError"),
    MANUAL_INSPECTION_ERROR("manualInspectionError"),
    MANUAL_INSPECTION("manualInspection"),
    ;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    TodoTypeEnum(String type) {
        this.type = type;
    }
}
