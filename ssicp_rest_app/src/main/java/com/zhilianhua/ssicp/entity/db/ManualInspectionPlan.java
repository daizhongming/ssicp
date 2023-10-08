package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ManualInspectionPlan {

    /**
     * 计划编号
     */
    private String planCode;

    /**
     * 巡检计划名称
     */
    @NotBlank(message = "计划名称不能为空")
    private String planName;

    /**
     * 巡检类型(枚举)
     */
    @NotBlank(message = "巡检类型不能为空")
    private String inspectionType;

    /**
     * 计划开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    /**
     * 巡检项
     */
    private String item;

    /**
     * 巡检状态(0.启动 1.停用)
     */
    private Integer inspectionStart;

    /**
     * 负责人名称
     */
    @NotBlank(message = "负责人名称不能为空")
    private String responsibleName;

    /**
     * 负责人名称
     */
    private String nickName;

    /**
     * 删除标记（0.未删除 1.删除）
     */
    private Integer deleteFlag;

    /**
     * 巡检次数
     */
    private Integer iterationCount;

    /**
     * 已完成次数
     */
    private Integer completedCount;

    /**
     * 发现异常次数
     */
    private Integer anomalyCount;

    /**
     * 处理异常次数
     */
    private Integer handledAnomalyCount;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 管段id集合
     */
    private String pipeIds;

    /**
     * 管线id集合
     */
    private String pipelineIds;

    /**
     * 巡检周期
     */
    @NotBlank(message = "巡检周期不能为空")
    private String cycle;

    /**
     * 巡检周期单位
     */
    @NotBlank(message = "巡检周期单位不能为空")
    private String cycleUnit;

    public ManualInspectionPlan() {
    }

}