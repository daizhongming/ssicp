package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 人工巡检实绩
 * @date 2023-08-14
 */
@Data
public class ManualInspectionOutcome implements Serializable {

    /**
     * 实绩编号
     */
    private String outcomeCode;

    /**
     * 计划编号
     */
    private String planCode;

    /**
     * 巡检计划名称
     */
    private String planName;

    /**
     * 实绩状态（0.正常 1.未处理 2.已处理 3.未做）
     */
    private Integer state;

    /**
     * 完成时间
     */
    private Date createTime;

    /**
     * 截至时间
     */
    private Date dueDate;

    /**
     * 修改时间（处理完成时间）
     */
    private Date updateTime;

    /**
     * 巡检次数
     */
    private Integer iterationNumber;

    /**
     * 负责人名称
     */
    private String responsibleName;
}