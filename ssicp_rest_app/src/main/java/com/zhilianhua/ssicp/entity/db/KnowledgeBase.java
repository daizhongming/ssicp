package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 知识库
 * @date 2023-08-18
 */
@Data
public class KnowledgeBase implements Serializable {

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
     * 删除标记（0.未删除 1.删除）
     */
    private Integer deleteFlag;

}