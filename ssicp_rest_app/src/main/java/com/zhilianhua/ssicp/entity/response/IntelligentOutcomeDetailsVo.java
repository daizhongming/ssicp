package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class IntelligentOutcomeDetailsVo {
    private String index;
    private String detailsId;
    private Double indexValue;
    private String alarmLevel;
    private Double lowerAlarm;
    private Double seniorAlarm;
    private Double seniorSeniorAlarm;
    private String parentDeviceType;
    private String parentDeviceTypeName;
    private String deviceId;
    private String deviceName;
    private String unit;
}
