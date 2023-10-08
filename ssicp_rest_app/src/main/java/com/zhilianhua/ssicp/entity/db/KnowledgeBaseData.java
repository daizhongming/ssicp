package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 知识明细
 * @date 2023-08-21
 */
@Data
public class KnowledgeBaseData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备id集合
     */
    private String deviceIds;

    /**
     * 指标
     */
    private String index;

    /**
     * 单位
     */
    private String unit;

    /**
     * 最高指标值
     */
    private String maxIndexValue;

    /**
     * 最低指标值
     */
    private String minIndexValue;

    /**
     * 知识库id
     */
    private String baseId;

}