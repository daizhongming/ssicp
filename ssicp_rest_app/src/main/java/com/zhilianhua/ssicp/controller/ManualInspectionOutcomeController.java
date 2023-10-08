package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.OutcomeDispose;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceIndexLogSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionOutcomeParams;
import com.zhilianhua.ssicp.entity.response.ManualInspectionOutcomeAppVo;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.ManualInspectionOutcomeService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/manualInspectionOutcomes")
public class ManualInspectionOutcomeController {
    @Autowired
    private ManualInspectionOutcomeService manualInspectionOutcomeService;

    @GetMapping("")
    @Privilege(value = "分页查询巡检实绩")
    public ResponseEntity findPage(ManualInspectionOutcomeParams manualInspectionOutcomeParams) {
        return ResponseEntityUtils.success(manualInspectionOutcomeService.findPage(manualInspectionOutcomeParams));
    }

    @GetMapping("/{outcomeCode}")
    @Privilege(value = "巡检结果详情")
    public ResponseEntity load(@PathVariable String outcomeCode) {
        return ResponseEntityUtils.success(manualInspectionOutcomeService.load(outcomeCode));
    }

    @PutMapping("/dispose/{outcomeCode}")
    @Privilege(value = "处理巡检结果")
    public ResponseEntity dispose(@RequestBody @Valid List<OutcomeDispose> outcomeDisposes, @PathVariable String outcomeCode) {
        manualInspectionOutcomeService.dispose(outcomeDisposes,outcomeCode);
        return ResponseEntityUtils.success();
    }

    @GetMapping("/getdispose/{outcomeCode}")
    @Privilege(value = "查询处理结果")
    public ResponseEntity getdispose(@PathVariable String outcomeCode){
        List<OutcomeDispose> outcomeDisposes = manualInspectionOutcomeService.getdispose(outcomeCode);
        return ResponseEntityUtils.success(outcomeDisposes);
    }


    @GetMapping("/indexLogsHistory")
    @Privilege("历史指标趋势")
    public ResponseEntity<List<DeviceIndexLog>> findIndexLogsHistory(String deviceId, String index) {
        return ResponseEntityUtils.success(manualInspectionOutcomeService.findIndexLogsHistory(deviceId,index));
    }
}