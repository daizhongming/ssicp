package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

@Data
public class IndexLog {
    /**
     * 指标记录id
     */
    private String indexLogId;

    /**
     * 指标记录id
     */
    private String alarmId;

    /**
     * 巡检对象
     */
    private String deviceName;

    /**
     * 巡检指标值
     */
    private String index;

    /**
     * 单位
     */
    private String unit;

    /**
     * 指标值
     */
    private Double indexValue;

    /**
     * 低报值
     */
    private Integer lowerAlarm;

    /**
     * 高报值
     */
    private Integer seniorAlarm;

    /**
     * 高高报值
     */
    private Integer seniorSeniorAlarm;

    /**
     * 设备类型
     */
    private String parentDeviceType;
}
