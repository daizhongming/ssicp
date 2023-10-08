package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.request.OutcomeDispose;
import com.zhilianhua.ssicp.entity.request.OutcomeDisposeDTO;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.InspectionExceptionsVO;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.IntelligentInspectionOutcomeService;
import com.zhilianhua.ssicp.service.impl.OutcomeManage;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 代忠明
 * @description 智能巡检结果
 * @date 2023-07-05
 */
@RestController
@Privilege("巡检实绩")
@RequestMapping(value = "/intelligentInspectionOutcomes")
public class IntelligentInspectionOutcomeController {
    @Autowired
    private IntelligentInspectionOutcomeService intelligentInspectionOutcomeService;

    @Autowired
    private OutcomeManage outcomeManage;

    @GetMapping("")
    @Privilege(value = "分页查询巡检结果")
    public ResponseEntity findPage(IntelligentInspectionSearchParams intelligentInspectionSearchParams) {
        return ResponseEntityUtils.success(intelligentInspectionOutcomeService.findPage(intelligentInspectionSearchParams));
    }

    @GetMapping("/{outcomeCode}")
    @Privilege(value = "查询巡检结果详细")
    public ResponseEntity load(@PathVariable String outcomeCode) {
        return ResponseEntityUtils.success(intelligentInspectionOutcomeService.load(outcomeCode));
    }

    @PutMapping("/dispose/{outcomeCode}")
    @Privilege(value = "处理巡检结果")
    public ResponseEntity dispose(@RequestBody @Valid OutcomeDisposeDTO outcomeDisposeDTO, @PathVariable String outcomeCode) {
        intelligentInspectionOutcomeService.dispose(outcomeDisposeDTO.getOutcomeDisposes(),outcomeCode);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/getdispose/{outcomeCode}")
    @Privilege(value = "查询巡检结果")
    public ResponseEntity getdispose(@PathVariable String outcomeCode) {
        List<OutcomeDispose> disposes = intelligentInspectionOutcomeService.getdispose(outcomeCode);
        return ResponseEntityUtils.success(disposes);
    }

    @GetMapping("queryExceptionToDos")
    @Privilege(value = "巡检异常代办整合")
    public ResponseEntity<InspectionExceptionsVO> queryExceptionToDos(){
        List<InspectionExceptionsVO> pageResultEntity = outcomeManage.queryExceptionToDos();
        return ResponseEntityUtils.success(pageResultEntity);
    }

}