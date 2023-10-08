package com.zhilianhua.ssicp.controller;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import com.zhilianhua.ssicp.entity.request.IntelligentInspectionPlanAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.IntelligentInspectionPlanService;
import com.zhilianhua.ssicp.service.JobService;
import com.zhilianhua.ssicp.utils.IdUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

/**
 * @author 代忠明
 * @description 智能巡检计划
 * @date 2023-07-05
 */
@RestController
@Privilege("巡检计划")
@RequestMapping(value = "/intelligentInspectionPlans")
public class IntelligentInspectionPlanController {
    @Autowired
    private IntelligentInspectionPlanService intelligentInspectionPlanService;

    @Autowired
    private JobService jobService;

    @GetMapping("")
    @Privilege(value = "分页查询巡检计划")
    public ResponseEntity pageList(IntelligentInspectionSearchParams intelligentInspectionSearchParams) {
        return ResponseEntityUtils.success(intelligentInspectionPlanService.pageList(intelligentInspectionSearchParams));
    }

    @PostMapping("")
    @Privilege(value = "新增巡检计划")
    public ResponseEntity add(@RequestBody @Valid IntelligentInspectionPlanAdd inspectionPlanAdd) {
        IntelligentInspectionPlan intelligentInspectionPlan = new IntelligentInspectionPlan();
        intelligentInspectionPlan.setPlanCode(IdUtils.uuid());
        intelligentInspectionPlan.setPlanName(inspectionPlanAdd.getPlanName());//计划名称
        intelligentInspectionPlan.setCycle(inspectionPlanAdd.getCycle());//巡检周期
        intelligentInspectionPlan.setCycleUnit(inspectionPlanAdd.getCycleUnit());
        intelligentInspectionPlan.setResponsibleName(inspectionPlanAdd.getResponsibleName());//负责人
        intelligentInspectionPlan.setStartTime(inspectionPlanAdd.getStartTime());//开始时间
        intelligentInspectionPlan.setInspectionTimes(inspectionPlanAdd.getInspectionTimes());//巡检时间集合
        intelligentInspectionPlan.setPipeIds(JSON.toJSONString(inspectionPlanAdd.getPipeIds()));//管段id集合
        intelligentInspectionPlan.setPipelineIds(inspectionPlanAdd.getPipelineIds());//管段id集合
//        intelligentInspectionPlan.setItem(inspectionPlanAdd.getItem());
        intelligentInspectionPlan.setInspectionStart(1);//任务默认状态
        intelligentInspectionPlan.setItem(JSON.toJSONString(inspectionPlanAdd.getMetricTemplateDTOS()));
        intelligentInspectionPlanService.add(intelligentInspectionPlan);
        jobService.add(intelligentInspectionPlanService.planToJob(intelligentInspectionPlan));//添加定时任务
        return ResponseEntityUtils.success();
    }

    @GetMapping("indicatortemplate")
    @Privilege(value = "巡检项模板")
    public ResponseEntity<Map<String, Set<String>>> getIndicatortemplate(String planCode){
        Map<String, Set<String>> templatemap = intelligentInspectionPlanService.getIndicatortemplate(planCode);
        return ResponseEntityUtils.success(templatemap);
    }

    @DeleteMapping("/{planCode}")
    @Privilege(value = "删除询价计划")
    public ResponseEntity delete(@PathVariable("planCode") String planCode) {
        intelligentInspectionPlanService.delete(planCode);
        jobService.delete(planCode, "IntelligentInspection");
        return ResponseEntityUtils.success();
    }

    @PutMapping("")
    @Privilege(value = "修改巡检计划")
    public ResponseEntity update(@RequestBody @Valid IntelligentInspectionPlanAdd inspectionPlanAdd) {
        IntelligentInspectionPlan intelligentInspectionPlan = new IntelligentInspectionPlan();
        intelligentInspectionPlan.setPlanCode(inspectionPlanAdd.getPlanCode());
        intelligentInspectionPlan.setPlanName(inspectionPlanAdd.getPlanName());//计划名称
        intelligentInspectionPlan.setCycle(inspectionPlanAdd.getCycle());//巡检周期
        intelligentInspectionPlan.setCycleUnit(inspectionPlanAdd.getCycleUnit());
        intelligentInspectionPlan.setResponsibleName(inspectionPlanAdd.getResponsibleName());//负责人
        intelligentInspectionPlan.setStartTime(inspectionPlanAdd.getStartTime());//开始时间
        intelligentInspectionPlan.setInspectionTimes(inspectionPlanAdd.getInspectionTimes());//巡检时间集合
        intelligentInspectionPlan.setPipeIds(JSON.toJSONString(inspectionPlanAdd.getPipeIds()));//管段id集合
        intelligentInspectionPlan.setPipelineIds(inspectionPlanAdd.getPipelineIds());//管段id集合
        intelligentInspectionPlan.setInspectionStart(1);//任务默认状态
        intelligentInspectionPlan.setItem(JSON.toJSONString(inspectionPlanAdd.getMetricTemplateDTOS()));
        intelligentInspectionPlanService.update(intelligentInspectionPlan);
        jobService.update(intelligentInspectionPlanService.planToJob(intelligentInspectionPlan));
        return ResponseEntityUtils.success();
    }

    @PutMapping("/upordown")
    @Privilege(value = "开启或关闭巡检计划")
    public ResponseEntity upordown(@RequestBody @Valid IntelligentInspectionPlan intelligentInspectionPlan) {
        intelligentInspectionPlanService.upordown(intelligentInspectionPlan);
        jobService.updateStatus(intelligentInspectionPlan.getPlanCode(), "IntelligentInspection", 1 - intelligentInspectionPlan.getInspectionStart());
        return ResponseEntityUtils.success();
    }

}