package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.request.DecisionPlanAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.DecisionPlanSearchParams;
import com.zhilianhua.ssicp.entity.request.DecisionPlanUpdate;
import com.zhilianhua.ssicp.entity.response.DecisionPlanVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

public interface DecisionPlanService {
    PageResultEntity<DecisionPlanVo> findPage(DecisionPlanSearchParams decisionPlanSearchParams);

    void add(DecisionPlanAdd decisionPlanAdd);

    void update(Integer id, DecisionPlanUpdate decisionPlanUpdate);

    void delete(Integer id);

    void updateReadCount(Integer id);
}
