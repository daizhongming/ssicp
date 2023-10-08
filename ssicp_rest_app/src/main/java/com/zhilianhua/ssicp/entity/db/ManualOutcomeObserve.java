package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 人工巡检观察想明细
 * @author zhengkai.blog.csdn.net
 * @date 2023-08-15
 */
@Data
public class ManualOutcomeObserve implements Serializable {

    /**
    * id
    */
    private String id;

    /**
    * 判定(0.正常 1.异常)
    */
    private Integer decide;

    /**
    * 观察项描述
    */
    private String decideDescription;

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