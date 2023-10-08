package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class DeviceAlarmLog {

    private Long id;

    private String deviceId;
    private String deviceName;
    private String deviceTypeName;

    private String indexId;
    private String indexName;

    private Double alarmValue;


    private Integer alarmLevel;


    private Date startTime;


    private Integer handleStatus;



    private Date handleTime;


    private String handleUserName;


    private String handleUserNickName;


    private Long handleCostTime;
    private String unit;
    private Double lowerAlarm;
    private Double seniorAlarm;
    private Double seniorSeniorAlarm;

    private static final Map<Integer, String> ALARM_LEVEL_MAP;

    static {
        ALARM_LEVEL_MAP = new HashMap();
        ALARM_LEVEL_MAP.put(0, "低报");
        ALARM_LEVEL_MAP.put(1, "高报");
        ALARM_LEVEL_MAP.put(2, "高高报");
    }

    public String getAlarmLevelName() {
        return ALARM_LEVEL_MAP.getOrDefault(getAlarmLevel(), "");
    }
}