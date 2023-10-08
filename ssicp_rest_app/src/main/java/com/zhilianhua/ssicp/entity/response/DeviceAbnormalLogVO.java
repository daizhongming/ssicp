package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class DeviceAbnormalLogVO {
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 指标id
     */
    private String indexId;


    /**
     * 设备父型号（1.仪表、2.阀门、3.疏水阀）
     */
    private Integer parentDeviceType;

    /**
     * 主键
     */
    private String id;

    /**
     * 指标
     */
    private String index;

    /**
     * 异常类型
     */
    private String abnormalType;

    /**
     * 指标值
     */
    private Double indexValue;

    /**
     * 校正值
     */
    private Double correction;

    /**
     * 报警级别
     */
    private int alarmLevel;

    /**
     * 备注
     */
    private String comments;
}
