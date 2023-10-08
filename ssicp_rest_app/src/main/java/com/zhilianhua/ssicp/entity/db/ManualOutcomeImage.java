package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description  人工巡检照片
 * @date 2023-08-15
 */
@Data
public class ManualOutcomeImage implements Serializable {

    /**
    * 主键
    */
    private String id;

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
    * 图片
    */
    private String fileList;

    /**
     * 设备类型
     */
    private String parentDeviceType;
    private String parentDeviceTypeName;

}