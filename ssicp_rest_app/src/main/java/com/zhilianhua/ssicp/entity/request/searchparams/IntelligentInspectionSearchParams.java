package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;

import java.util.List;

@Data
public class IntelligentInspectionSearchParams extends PageParams {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 负责人名称
     */
    private String responsibleName;

    /**
     * 确认状态
     */
    private String confirm;

    /**
     * 计划编号
     */
    private String planCode;

    /**
     * 巡检结果
     */
    private String outcomeType;

    /**
     * 巡检结果
     */
    private String inspectionStart;

    /**
     * 处理状态
     */
    private String actionStatus;

    /**
     * 处理状态
     */
    private List<String> Status;
}
