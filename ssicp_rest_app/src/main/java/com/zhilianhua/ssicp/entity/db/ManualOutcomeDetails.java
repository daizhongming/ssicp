package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 人工巡检收集项明细
 * @date 2023-08-15
 */
@Data
public class ManualOutcomeDetails implements Serializable {

    /**
    * 主键
    */
    private String id;

    /**
    * 指标值
    */
    private Double indexValue;

    /**
    * 指标
    */
    private String index;

    /**
    * 单位
    */
    private String unit;

    /**
    * 报警级别(alarm_level)
    */
    private Integer alarmLevel;

    /**
    * 设备id
    */
    private String deviceId;

    /**
    * 设备名称
    */
    private String deviceName;

    /**
     * 处理id
     */
    private String infoId;

    /**
    * 实绩id
    */
    private String outcomeCode;

    /**
     * 设备类型
     */
    private String parentDeviceType;
    private String parentDeviceTypeName;

}