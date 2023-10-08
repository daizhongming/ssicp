package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @description device_steam_traps
 * @date 2023-08-29
 */
@Data
public class DeviceSteamTraps implements Serializable {

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
    * 所属管段
    */
    @NotBlank(message = "所属管段不能为空")
    private String pid;

    /**
    * 安装位置
    */
    @NotBlank(message = "安装位置不能为空")
    private String locationDescription;

    public DeviceSteamTraps() {
    }

}