package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.ManualInspectionPlan;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionPlanSearchParams;
import com.zhilianhua.ssicp.entity.response.ManualInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

public interface ManualInspectionPlanMapper {

    PageResultEntity<ManualInspectionPlanVO> search(ManualInspectionPlanSearchParams manualInspectionPlanSearchParams);

    void add(ManualInspectionPlan manualInspectionPlan);

    void update(ManualInspectionPlan manualInspectionPlan);

    void delete(@Param("planCode") String planCode);

    ManualInspectionPlan getByid(@Param("id") String id);
}