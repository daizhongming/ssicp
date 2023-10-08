package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 智能巡检计划
 * @author zhengkai.blog.csdn.net
 * @date 2023-08-07
 */
@Data
public class IntelligentInspectionPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 计划编号
    */
    private String planCode;

    /**
    * 计划名称
    */
    private String planName;

    /**
    * 管段id集合
    */
    private String pipeIds;

    /**
     * 管段id集合
     */
    private String pipelineIds;

    /**
    * 巡检次数
    */
    private Integer iterationNumber;

    /**
    * 巡检次数
    */
    private Integer nonZeroCount;

    /**
    * 巡检次数
    */
    private Integer totalCount;

    /**
     * 巡检周期
     */
    private String cycle;

    /**
     * 巡检单位
     */
    private String cycleUnit;

    /**
     * 巡检项
     */
    private String item;

    /**
    * 开始时间
    */
    private Date startTime;

    /**
    * 负责人名称
    */
    private String responsibleName;

    /**
    * 巡检时间集合
    */
    private String inspectionTimes;

    /**
    * 巡检状态
    */
    private Integer inspectionStart;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
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