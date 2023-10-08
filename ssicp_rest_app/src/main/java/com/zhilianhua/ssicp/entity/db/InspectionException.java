package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description 巡检异常表
 * @author zhengkai.blog.csdn.net
 * @date 2023-07-11
 */
@Data
public class InspectionException implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 巡检任务名称
     */
    private String planName;

    /**
     * 巡检计划结果id
     */
    private String outcomeCode;

    /**
     * 巡检类型
     */
    private Integer inspectionType;

    /**
     * 管线id
     */
    private String pipelineId;

    /**
     * 管线名称
     */
    private String pipelineName;

    /**
     * 起始位置
     */
    private String startPosition;

    /**
     * 结束位置
     */
    private String endPosition;

    /**
     * 异常描述
     */
    private String describe;

    /**
     * 异常类型
     */
    private String abnormalTypes;

    /**
     * 异常位置
     */
    private String abnormalLocation;

    /**
     * 异常次数(天/次)
     */
    private Integer abnormalNumber;

    /**
     * 处理状态（0.未处理 1.已处理 3.已忽略）
     */
    private Integer disposeState;

    /**
     * 原因
     */
    private String cause;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Long createdById;

    /**
     * 修改人
     */
    private Long updatedById;

    /**
     * 删除标记（0.未删除 1.删除）
     */
    private Integer deleteFlag;

    /**
     * 备注
     */
    private String comments;

    public InspectionException() {
    }

}