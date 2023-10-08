package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import com.zhilianhua.ssicp.entity.request.IntelligentInspectionPlanAdd;
import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.IntelligentInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.Map;
import java.util.Set;

/**
 * @description 智能巡检计划
 * @author 代忠明
 * @date 2023-07-05
 */
public interface IntelligentInspectionPlanService {

    JobAdd planToJob(IntelligentInspectionPlan intelligentInspectionPlan);

    void add(IntelligentInspectionPlan intelligentInspectionPlan);

    PageResultEntity<IntelligentInspectionPlanVO> pageList(IntelligentInspectionSearchParams intelligentInspectionSearchParams);

    Map<String, Set<String>> getIndicatortemplate(String planCode);

    void update(IntelligentInspectionPlan intelligentInspectionPlan);

    void upordown(IntelligentInspectionPlan intelligentInspectionPlan);

    void delete(String planCode);

    IntelligentInspectionPlan findByid(String id);

}