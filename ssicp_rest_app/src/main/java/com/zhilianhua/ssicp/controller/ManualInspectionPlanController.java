package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.ManualInspectionPlan;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionPlanSearchParams;
import com.zhilianhua.ssicp.entity.response.ManualInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.JobService;
import com.zhilianhua.ssicp.service.ManualInspectionPlanService;
import com.zhilianhua.ssicp.utils.IdUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/manualInspectionPlans")
public class ManualInspectionPlanController {
    @Autowired
    private ManualInspectionPlanService manualInspectionPlanService;

    @Autowired
    private JobService jobService;

    @GetMapping("")
    @Privilege(value = "分页查询")
    public ResponseEntity<PageResultEntity<ManualInspectionPlanVO>> findPage(ManualInspectionPlanSearchParams manualInspectionPlanSearchParams) {
        return ResponseEntityUtils.success(manualInspectionPlanService.findPage(manualInspectionPlanSearchParams));
    }

    @PostMapping("")
    @Privilege(value = "新增巡检计划")
    public ResponseEntity add(@RequestBody @Valid ManualInspectionPlan manualInspectionPlan) {
        manualInspectionPlan.setPlanCode(IdUtils.uuid());
        manualInspectionPlan.setInspectionStart(0);
        manualInspectionPlanService.add(manualInspectionPlan);
        jobService.add(manualInspectionPlanService.planToJob(manualInspectionPlan));//添加定时任务
        return ResponseEntityUtils.success();
    }

    @PutMapping("")
    @Privilege(value = "修改巡检计划")
    public ResponseEntity update(@RequestBody @Valid ManualInspectionPlan manualInspectionPlan) {
        manualInspectionPlanService.update(manualInspectionPlan);
        jobService.update(manualInspectionPlanService.planToJob(manualInspectionPlan));
        return ResponseEntityUtils.success();
    }

    @PutMapping("upordown")
    @Privilege(value = "开启或关闭巡检计划")
    public ResponseEntity upordown(@RequestBody @Valid ManualInspectionPlan manualInspectionPlan) {
        manualInspectionPlanService.update(manualInspectionPlan);
        jobService.updateStatus(manualInspectionPlan.getPlanCode(),"ManualInspection",1-manualInspectionPlan.getInspectionStart());
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("{planCode}")
    @Privilege(value = "删除巡检计划")
    public ResponseEntity delete(@PathVariable String planCode) {
        ManualInspectionPlan manualInspectionPlan = new ManualInspectionPlan();
        manualInspectionPlan.setPlanCode(planCode);
        manualInspectionPlan.setDeleteFlag(1);
        manualInspectionPlanService.update(manualInspectionPlan);
        jobService.delete(planCode, "ManualInspection");
        return ResponseEntityUtils.success();
    }


}