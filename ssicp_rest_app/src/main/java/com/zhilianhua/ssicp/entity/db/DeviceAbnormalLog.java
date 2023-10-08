package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceAbnormalLog implements Serializable {

    /**
    * 主键
    */
    private String id;

    /**
    * 报警级别
    */
    private int alarmLevel;

    /**
    * 异常记录id
    */
    private String exceptionId;

    /**
    * 指标记录id
    */
    private String indexLogId;

    /**
    * 异常类型
    */
    private String abnormalType;

    /**
    * 备注
    */
    private String comments;

    /**
    * 校正值
    */
    private Double correction;

    public DeviceAbnormalLog() {
    }

}