package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @description 仪表管理
 * @date 2023-08-24
 */
@Data
public class DeviceInstrument implements Serializable {

    /**
    * 主键
    */
    private String id;

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

    /**
    * 管段入口
    */
    @NotBlank(message = "管段入口不能为空")
    private String pipeInlet;

    /**
    * 管段出口
    */
    @NotBlank(message = "管段出口不能为空")
    private String pipeExit;

}