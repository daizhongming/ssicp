package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description 管段表
 * @date 2023-08-23
 */
@Data
public class Pipe implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 管段编号
     */
    @NotBlank(message = "管段编号不能为空")
    private String pipeCode;

    /**
     * 管段名称
     */
    @NotBlank(message = "管段编号不能为空")
    private String pipeName;

    /**
     * 起始位置
     */
    private String startPosition;

    /**
     * 结束位置
     */
    private String endPosition;

    /**
     * 图纸编号
     */
    @NotBlank(message = "图纸编号不能为空")
    private String drawingCode;

    /**
     * 长度（m）
     */
    @NotNull(message = "管段长度不能为空")
    private Double pipeLength;

    /**
     * 管道壁厚（mm）
     */
    @NotNull(message = "管道壁厚不能为空")
    private Double pipeThickness;

    /**
     * 壁厚系列
     */
    private String thicknessSeries;

    /**
     * 安全等级
     */
    @NotBlank(message = "安全等级不能为空")
    private String safeGrade;

    /**
     * 公称直径
     */
    @NotNull(message = "公称直径不能为空")
    private Double nominalDiameter;

    /**
     * 公顷外径
     */
    @NotNull(message = "公顷外径不能为空")
    private Double nominalOd;

    /**
     * 管段材料
     */
    private String pipeMaterial;

    /**
     * 设备id
     */
    private String deviceId;


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
    private String createdByName;

    /**
     * 修改人
     */
    private String updatedByName;

    /**
     * 删除标记（0.未删除 1.删除）
     */
    private Integer deleteFlag;

    /**
     * 备注
     */
    private String comments;

}