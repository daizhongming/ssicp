package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import lombok.Data;

@Data
public class IntelligentInspectionPlanVO extends IntelligentInspectionPlan {

    /**
     * 计划名称
     */
    private String planName;

    /**
     * 管线id集合
     */
    private String pipelineIds;

}
