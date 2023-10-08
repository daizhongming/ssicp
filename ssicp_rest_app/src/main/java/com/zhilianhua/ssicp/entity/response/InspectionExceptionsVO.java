package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.Date;

@Data
public class InspectionExceptionsVO {
    /**
     * 实绩编号
     */
    private String outcomeCode;

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 巡检类型
     */
    private String outcomeType;

    /**
     * 巡检项
     */
    private Integer indexCount;

    /**
     * 异常发现数量
     */
    private Integer exceptionCount;

    /**
     * 异常发现时间
     */
    private Date createTime;

    /**
     * 负责人名称
     */
    private String responsibleName;

    /**
     * 实绩状态（0.正常 1.未处理 2.已处理）
     */
    private Integer state;
}
