package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.BenefitAnalysisReport;
import com.zhilianhua.ssicp.entity.db.DecisionPlan;
import com.zhilianhua.ssicp.entity.request.DecisionPlanAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.DecisionPlanSearchParams;
import com.zhilianhua.ssicp.entity.request.DecisionPlanUpdate;
import com.zhilianhua.ssicp.entity.response.DecisionPlanVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

public interface DecisionPlanMapper {

    PageResultEntity<DecisionPlanVo> search(DecisionPlanSearchParams decisionPlanSearchParams);

    void add(DecisionPlanAdd decisionPlanAdd);

    void delete(@Param("id") Integer id);
    DecisionPlan findById(@Param("id") Integer id);

    void update(@Param("id") Integer id, @Param("params") DecisionPlanUpdate decisionPlanUpdate);

    void updateReadCount(@Param("id") Integer id);
}
