package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @description 管线表
 * @date 2023-08-22
 */
@Data
public class Pipeline implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 管线名称
     */
    @NotBlank(message = "管线名称不能为空")
    private String pipelineName;

    /**
     * 管线类型
     */
    @NotBlank(message = "管线类型不能为空")
    private String pipelineType;

    /**
     * 压力等级（mpa）
     */
    @NotBlank(message = "压力等级不能为空")
    private String pressureRating;

    /**
     * 区域划分
     */
    @NotBlank(message = "区域划分不能为空")
    private String region;

    /**
     * 起始位置
     */
    private String startPosition;

    /**
     * 结束位置
     */
    private String endPosition;

    /**
     * 管段id集合
     */
    private String pipeIds;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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

    /**
     * 长度
     */
    private Long pipelineLength;

    /**
     * 管段数量
     */
    private Long pipeCount;

    /**
     * 弯头数量
     */
    private Long elbowCount;

    /**
     * 补偿器数量
     */
    private Long compensatorCount;

    /**
     * 阀门数量
     */
    private Long valveCount;

    /**
     * 仪表数量
     */
    private Long instrumentCount;

}