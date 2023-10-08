package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceAlarmLogAlarmDetailsVo {
    private String indexId;
    private String indexName;
    private Double alarmValue;
    private String unit;
    private Integer alarmLevel;
    private Double lowerAlarm;
    private Double seniorAlarm;
    private Double seniorSeniorAlarm;
    private Date startTime;
}