package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class PipeVO {
    /**
     * 主键
     */
    private String id;

    /**
     * 管段编号
     */
    private String pipeCode;

    /**
     * 管段名称
     */
    private String pipeName;

    /**
     * 设备id
     */
    private String deviceId;


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
    private String drawingCode;

    /**
     * 长度（m）
     */
    private Long pipeLength;

    /**
     * 使用年限（年）
     */
    private Integer usageDuration;

    /**
     * 管道壁厚（mm）
     */
    private Long pipeThickness;

    /**
     * 安全等级
     */
    private String safeGrade;

    /**
     * 保温层材料
     */
    private String insulationMaterial;

    /**
     * 保温厚度（mm）
     */
    private Integer insulationMaterialThickness;

    /**
     * 投用日期
     */
    private Date useTime;

    /**
     * 质检日期
     */
    private Date qcDate;
}
