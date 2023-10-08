package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceSteamTrapsVO {
    /**
     * 主键
     */
    private String id;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备编号
     */
    private String deviceCode;

    /**
     * 设备父型号（device_type）
     */
    private Integer parentDeviceType;

    /**
     * 设备子类型
     */
    private String deviceType;

    /**
     * 所属管段
     */
    private String pid;

    /**
     * 安装位置
     */
    private String locationDescription;

    /**
     * 工作年限
     */
    private Integer workYears;

    /**
     * 投用日期
     */
    private Date useTime;

    /**
     * 使用年限
     */
    private Integer usageDuration;

    /**
     * 检查周期（月）
     */
    private String checkPeriod;

    /**
     * 质检日期
     */
    private Date qcDate;

}
