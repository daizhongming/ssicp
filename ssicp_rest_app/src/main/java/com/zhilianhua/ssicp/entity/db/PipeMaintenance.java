package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 管段维护信息
 * @author zhengkai.blog.csdn.net
 * @date 2023-08-23
 */
@Data
public class PipeMaintenance implements Serializable {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 管段id
     */
    private String pipeId;

    /**
     * 工作年限（年）
     */
    private Integer workingYears;

    /**
     * 投用日期
     */
    private Date useTime;

    /**
     * 使用年限（年）
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
     * 铺设方式
     */
    private String layingMethod;
}