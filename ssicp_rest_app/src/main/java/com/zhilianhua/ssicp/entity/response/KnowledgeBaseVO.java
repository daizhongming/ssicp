package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class KnowledgeBaseVO {
    /**
     * 主键
     */
    private String id;

    /**
     * 知识标题
     */
    private String title;

    /**
     * 问题现象
     */
    private String question;

    /**
     * 解决方案
     */
    private String scheme;

    /**
     * 装置范围
     */
    private String deviceRange;

    /**
     * 指标范围
     */
    private String indicatorRange;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 作者
     */
    private String responsibleName;

    /**
     * 查询次数
     */
    private Integer readCount;

    /**
     * 推送次数
     */
    private Integer pushCount;

    /**
     * 有效性占比（无/有）
     */
    private String validityPercentage;
}
