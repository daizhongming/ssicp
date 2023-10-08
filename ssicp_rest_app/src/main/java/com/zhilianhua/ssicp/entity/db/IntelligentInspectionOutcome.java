package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 智能巡检实绩（结果）
 * @author zhengkai.blog.csdn.net
 * @date 2023-08-09
 */
@Data
public class IntelligentInspectionOutcome implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 巡检结果编号
    */
    private String outcomeCode;

    /**
    * 巡检计划编号
    */
    private String planCode;

    /**
     * 巡检计划编号
     */
    private String planName;

    /**
    * 巡检次数
    */
    private int iterationNumber;

    /**
    * 实绩状态（0.正常 1.未处理 2.已处理）
    */
    private Integer state;

    /**
    * 负责人名称
    */
    private String responsibleName;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 删除标记（0.未删除 1.删除）
    */
    private Integer deleteFlag;

    /**
    * 备注
    */
    private String comments;

}