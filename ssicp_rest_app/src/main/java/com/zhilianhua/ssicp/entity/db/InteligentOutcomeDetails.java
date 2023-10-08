package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 巡检记录明细
 * @date 2023-08-09
 */
@Data
public class InteligentOutcomeDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long id;

    /**
    * 指标值
    */
    private Double indexValue;

    /**
    * 报警级别
    */
    private Integer alarmLevel;

    /**
    * 指标
    */
    private String index;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 单位
     */
    private String unit;

    /**
    * 实绩id
    */
    private String outcomeCode;

    /**
    * 备注
    */
    private String comments;

    /**
    * 处理id
    */
    private String infoId;

    public InteligentOutcomeDetails() {
    }

}