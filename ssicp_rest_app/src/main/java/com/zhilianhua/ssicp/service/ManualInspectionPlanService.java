package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.ManualInspectionPlan;
import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionPlanSearchParams;
import com.zhilianhua.ssicp.entity.response.ManualInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

public interface ManualInspectionPlanService {

    PageResultEntity<ManualInspectionPlanVO> findPage(ManualInspectionPlanSearchParams manualInspectionPlanSearchParams);

    void add(ManualInspectionPlan manualInspectionPlan);

    void update(ManualInspectionPlan manualInspectionPlan);

    ManualInspectionPlan getByid(String id);

    JobAdd planToJob(ManualInspectionPlan manualInspectionPlan);
}