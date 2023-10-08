package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.DecisionPlan;
import com.zhilianhua.ssicp.entity.request.DecisionPlanAdd;
import com.zhilianhua.ssicp.entity.request.DecisionPlanUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.DecisionPlanSearchParams;
import com.zhilianhua.ssicp.entity.response.DecisionPlanVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.DecisionPlanMapper;
import com.zhilianhua.ssicp.service.DecisionPlanService;
import com.zhilianhua.ssicp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DecisionPlanServiceImpl implements DecisionPlanService {
    @Autowired
    private DecisionPlanMapper decisionPlanMapper;
    @Autowired
    private FileService fileService;

    @Override
    public PageResultEntity<DecisionPlanVo> findPage(DecisionPlanSearchParams decisionPlanSearchParams) {
        return decisionPlanMapper.search(decisionPlanSearchParams);
    }

    @Override
    public void add(DecisionPlanAdd decisionPlanAdd) {
        decisionPlanMapper.add(decisionPlanAdd);
    }

    @Override
    public void update(Integer id, DecisionPlanUpdate decisionPlanUpdate) {
        decisionPlanMapper.update(id, decisionPlanUpdate);
    }

    @Override
    public void delete(Integer id) {
        DecisionPlan byId = decisionPlanMapper.findById(id);
        if (Objects.nonNull(byId)) {
            decisionPlanMapper.delete(id);
            fileService.deleteByCode(byId.getFileCode());
        }
    }

    @Override
    public void updateReadCount(Integer id) {
        decisionPlanMapper.updateReadCount(id);
    }
}
