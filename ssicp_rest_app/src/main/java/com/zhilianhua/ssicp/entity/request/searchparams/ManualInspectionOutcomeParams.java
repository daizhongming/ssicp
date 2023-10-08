package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ManualInspectionOutcomeParams extends OrderPageParams {
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
     * 巡检结果
     */
    private String outcomeType;

    /**
     * 计划编号
     */
    private String planCode;

    /**
     * 处理状态
     */
    private String actionStatus;

    /**
     * 处理状态
     */
    private List<String> Status;


    /**
     * 查询类型
     */
    private String queryFlag;

    @Override
    public Map<String, String> setPropsMap() {
        return null;
    }
}
