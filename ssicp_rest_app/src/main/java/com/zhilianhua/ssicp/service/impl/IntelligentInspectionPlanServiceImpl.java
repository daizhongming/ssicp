package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import com.zhilianhua.ssicp.entity.request.IntelligentInspectionPlanAdd;
import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmDeviceVO;
import com.zhilianhua.ssicp.entity.response.IntelligentInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.IntelligentInspectionPlanMapper;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.task.impl.IntelligentInspectionJob;
import com.zhilianhua.ssicp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 代忠明
 * @description 智能巡检计划
 * @date 2023-07-05
 */
@Service
public class IntelligentInspectionPlanServiceImpl implements IntelligentInspectionPlanService {
    private static final String GROUP_NAME = "IntelligentInspection";
    @Autowired
    private IntelligentInspectionPlanMapper intelligentInspectionPlanMapper;

    @Override
    public JobAdd planToJob(IntelligentInspectionPlan intelligentInspectionPlan) {
        JobAdd jobAdd = new JobAdd();
        jobAdd.setJobClass(IntelligentInspectionJob.class.getName());
        jobAdd.setJobGroupName(GROUP_NAME);
        jobAdd.setDescription(intelligentInspectionPlan.getPlanName());
        jobAdd.setStartTime(intelligentInspectionPlan.getStartTime());
        jobAdd.setJobName(intelligentInspectionPlan.getPlanCode());
        jobAdd.setCronExpressions(DateUtils.parseCronExpressions(intelligentInspectionPlan.getInspectionTimes()));
        jobAdd.setTimeIntervalString(intelligentInspectionPlan.getCycle()+";"+intelligentInspectionPlan.getCycleUnit());
        jobAdd.setStatus(1 - intelligentInspectionPlan.getInspectionStart());
        jobAdd.setJobDataMap(JSON.toJSONString(intelligentInspectionPlan));
        return jobAdd;
    }

    @Override
    public void add(IntelligentInspectionPlan intelligentInspectionPlan) {
        intelligentInspectionPlanMapper.insert(intelligentInspectionPlan);//添加计划
    }

    @Override
    public void update(IntelligentInspectionPlan intelligentInspectionPlan) {
        intelligentInspectionPlanMapper.update(intelligentInspectionPlan);//修改计划
    }

    @Override
    public void upordown(IntelligentInspectionPlan intelligentInspectionPlan) {
        intelligentInspectionPlanMapper.update(intelligentInspectionPlan);
    }

    @Override
    public void delete(String planCode) {
        intelligentInspectionPlanMapper.delete(planCode);
    }

    @Override
    public IntelligentInspectionPlan findByid(String id) {
        return intelligentInspectionPlanMapper.findByid(id);
    }

    @Override
    public PageResultEntity<IntelligentInspectionPlanVO> pageList(IntelligentInspectionSearchParams intelligentInspectionSearchParams) {
        return intelligentInspectionPlanMapper.pageList(intelligentInspectionSearchParams);
    }

    @Override
    public Map<String, Set<String>> getIndicatortemplate(String planCode) {
//        //查询指标
//        IntelligentInspectionPlan inspectionPlan = intelligentInspectionPlanMapper.findByid(planCode);
//        List<String> strings = JSON.parseArray(inspectionPlan.getDeviceAlarmIds(), String.class);
//        List<DeviceAlarmDeviceVO> deviceAlarmDeviceVOS = deviceAlarmService.getIndicatortemplate(strings);
//        Map<String, Set<String>> setMap = deviceAlarmDeviceVOS.stream().collect(Collectors.groupingBy(deviceAlarmDeviceVO -> {
//            String index = deviceAlarmDeviceVO.getParentDeviceType();
//            String s = new String();
//            if ("1".equals(index)) {//仪表指标
//                s = "instrument_index";
//            } else if ("2".equals(index)) {//阀门指标
//                s = "steam_traps_index";
//            } else if ("3".equals(index)) {//疏水阀指标
//                s = "valve_index";
//            } else if ("4".equals(index)) {//管段指标
//                s = "valve_index";
//            } else if ("5".equals(index)) {//产汽点指标
//                s = "steam_production_point_index";
//            } else if ("6".equals(index)) {//用汽点指标
//                s = "with_steam_spots_index";
//            } else if ("7".equals(index)) {//补偿器指标
//                s = "compensator_index";
//            }
//            return s;
//        }, Collectors.mapping(DeviceAlarmDeviceVO::getIndex, Collectors.toSet())));
//
//        return setMap;
        return null;
    }

}