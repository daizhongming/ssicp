package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class DeviceGasPointVO {
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
     * 所属区域
     */
    private String region;

    /**
     * 出入口管道
     */
    private String pid;
}
