package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.zhilianhua.ssicp.entity.db.ManualInspectionPlan;
import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionPlanSearchParams;
import com.zhilianhua.ssicp.entity.response.ManualInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.ManualInspectionPlanMapper;
import com.zhilianhua.ssicp.service.ManualInspectionPlanService;
import com.zhilianhua.ssicp.task.impl.IntelligentInspectionJob;
import com.zhilianhua.ssicp.task.impl.ManualInspectionJob;
import com.zhilianhua.ssicp.utils.DateUtils;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManualInspectionPlanServiceImpl implements ManualInspectionPlanService {
    @Autowired
    private ManualInspectionPlanMapper manualInspectionPlanMapper;

    private static final String GROUP_NAME = "ManualInspection";

    @Override
    public PageResultEntity<ManualInspectionPlanVO> findPage(ManualInspectionPlanSearchParams manualInspectionPlanSearchParams) {
        return manualInspectionPlanMapper.search(manualInspectionPlanSearchParams);
    }

    @Override
    public void add(ManualInspectionPlan manualInspectionPlan) {
        manualInspectionPlanMapper.add(manualInspectionPlan);
    }

    @Override
    public void update(ManualInspectionPlan manualInspectionPlan) {
        manualInspectionPlanMapper.update(manualInspectionPlan);
    }

    @Override
    public ManualInspectionPlan getByid(String id) {
        return manualInspectionPlanMapper.getByid(id);
    }

    @Override
    public JobAdd planToJob(ManualInspectionPlan manualInspectionPlan) {
        JobAdd jobAdd = new JobAdd();
        jobAdd.setJobClass(ManualInspectionJob.class.getName());
        jobAdd.setJobGroupName(GROUP_NAME);
        jobAdd.setDescription(manualInspectionPlan.getPlanName());
        jobAdd.setStartTime(manualInspectionPlan.getStartTime());
        jobAdd.setJobName(manualInspectionPlan.getPlanCode());
        jobAdd.setTimeIntervalString(manualInspectionPlan.getCycle()+";"+manualInspectionPlan.getCycleUnit());
        List<String> timeList = new ArrayList<>();
        timeList.add(DateUtils.formatDateTime(manualInspectionPlan.getStartTime()));
//        jobAdd.setCronExpressions(DateUtils.parseCronExpressions(JSONArray.toJSONString(timeList)));
        jobAdd.setStatus(1 - manualInspectionPlan.getInspectionStart());
        jobAdd.setJobDataMap(JSON.toJSONString(manualInspectionPlan));
        return jobAdd;
    }
}