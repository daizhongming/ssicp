package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.ManualInspectionOutcome;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeDetails;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeObserve;
import com.zhilianhua.ssicp.entity.response.InspectionExceptionsVO;
import com.zhilianhua.ssicp.entity.response.IntelligentOutcomeDetailsVo;
import com.zhilianhua.ssicp.service.InteligentOutcomeDetailsService;
import com.zhilianhua.ssicp.service.IntelligentInspectionOutcomeService;
import com.zhilianhua.ssicp.service.ManualInspectionOutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutcomeManage {
    @Autowired
    private IntelligentInspectionOutcomeService intelligentInspectionOutcomeService;

    @Autowired
    private ManualInspectionOutcomeService manualInspectionOutcomeService;

    @Autowired
    private InteligentOutcomeDetailsService inteligentOutcomeDetailsService;

    public List<InspectionExceptionsVO> queryExceptionToDos() {
        //查询智能巡检异常
        List<InspectionExceptionsVO> inspectionExceptionsVOS = intelligentInspectionOutcomeService.queryExceptionToDos();
        inspectionExceptionsVOS.forEach(inspectionExceptionsVO -> {
            List<IntelligentOutcomeDetailsVo> intelligentOutcomeDetailsVos = inteligentOutcomeDetailsService.selectByoutcomeCode(inspectionExceptionsVO.getOutcomeCode());
            inspectionExceptionsVO.setIndexCount(intelligentOutcomeDetailsVos.size());
            inspectionExceptionsVO.setExceptionCount((int) intelligentOutcomeDetailsVos.stream().filter(intelligentOutcomeDetailsVo -> !("3".equals(intelligentOutcomeDetailsVo.getAlarmLevel()))).count());
            inspectionExceptionsVO.setOutcomeType("1");
        });

        //查询人工巡检异常
        List<InspectionExceptionsVO> inspectionExceptionsVOS1 = manualInspectionOutcomeService.queryExceptionToDos();
        inspectionExceptionsVOS1.forEach(inspectionExceptionsVO -> {
            List<ManualOutcomeObserve> manualOutcomeObserves = manualInspectionOutcomeService.observeByoutcomeCode(inspectionExceptionsVO.getOutcomeCode());
            List<ManualOutcomeDetails> manualOutcomeDetails = manualInspectionOutcomeService.detailsByoutcomeCode(inspectionExceptionsVO.getOutcomeCode());
            inspectionExceptionsVO.setOutcomeType("0");
            inspectionExceptionsVO.setIndexCount(manualOutcomeObserves.size() + manualOutcomeDetails.size());
            inspectionExceptionsVO.setExceptionCount((int) (manualOutcomeDetails.stream().filter(manualOutcomeDetails1 -> !("3".equals(manualOutcomeDetails1.getAlarmLevel()))).count() +
                    manualOutcomeObserves.stream().filter(manualOutcomeObserve -> "1".equals(manualOutcomeObserve.getDecide())).count()));
        });

        //整合
        inspectionExceptionsVOS1.addAll(inspectionExceptionsVOS);
        return inspectionExceptionsVOS1;
    }
}