package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.app.MessageTypeEnum;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionOutcome;
import com.zhilianhua.ssicp.entity.request.OutcomeDispose;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.IntelligentInspectionOutcomeMapper;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 代忠明
 * @description 智能巡检结果
 * @date 2023-07-05
 */
@Service
public class IntelligentInspectionOutcomeServiceImpl implements IntelligentInspectionOutcomeService {
    @Autowired
    private IntelligentInspectionOutcomeMapper intelligentInspectionOutcomeMapper;

    @Autowired
    private InteligentOutcomeDetailsService inteligentOutcomeDetailsService;

    @Autowired
    private AlarmactionInfoService alarmactionInfoService;

    @Autowired
    private MessageService messageServicel;

    @Override
    public PageResultEntity<IntelligentInspectionOutcomeVO> findPage(IntelligentInspectionSearchParams intelligentInspectionSearchParams) {
        if ("1".equals(intelligentInspectionSearchParams.getOutcomeType())){
            intelligentInspectionSearchParams.setStatus(Collections.singletonList("0"));
        } else if ("2".equals(intelligentInspectionSearchParams.getOutcomeType())) {
            if ("1".equals(intelligentInspectionSearchParams.getActionStatus())){
                intelligentInspectionSearchParams.setStatus(Collections.singletonList("1"));
            } else if ("2".equals(intelligentInspectionSearchParams.getActionStatus())) {
                intelligentInspectionSearchParams.setStatus(Collections.singletonList("2"));
            }
            else {
                intelligentInspectionSearchParams.setStatus(Stream.of("1", "2").collect(Collectors.toList()));
            }
        }
        return intelligentInspectionOutcomeMapper.search(intelligentInspectionSearchParams);
    }

    @Override
    public void insert(IntelligentInspectionOutcome intelligentInspectionOutcome) {
        intelligentInspectionOutcomeMapper.insert(intelligentInspectionOutcome);
    }

    @Override
    public Map<String, Map<String, List<IntelligentOutcomeDetailsVo>>> load(String outcomeCode) {
        Map<String, Object> hashMap = new HashMap<>();
        //查询明细
        List<IntelligentOutcomeDetailsVo> outcomeDetails = inteligentOutcomeDetailsService.selectByoutcomeCode(outcomeCode);
        Map<String, Map<String, List<IntelligentOutcomeDetailsVo>>> collect = outcomeDetails.stream().collect(Collectors.groupingBy(IntelligentOutcomeDetailsVo::getParentDeviceType, Collectors.groupingBy(IntelligentOutcomeDetailsVo::getDeviceId)));
//        hashMap.put("details",collect);
        return collect;
    }

    @Override
    public void dispose(List<OutcomeDispose> outcomeDisposes, String outcomeCode) {
        AlarmactionInfo alarmactionInfo = new AlarmactionInfo();
        for (OutcomeDispose outcomeDispose : outcomeDisposes) {
            alarmactionInfo.setId(IdUtils.uuid());
            alarmactionInfo.setSummary(outcomeDispose.getSummary());
            alarmactionInfo.setResolutionReason(outcomeDispose.getResolutionReason());
            alarmactionInfo.setRemarks(outcomeDispose.getRemarks());
            alarmactionInfo.setFileList(String.join(";",outcomeDispose.getFileList()));
            alarmactionInfo.setFileListBefore(String.join(";",outcomeDispose.getFileListBefore()));
            alarmactionInfoService.add(alarmactionInfo);
            for (String s : outcomeDispose.getDetailsId()) {
                InteligentOutcomeDetails inteligentOutcomeDetails = new InteligentOutcomeDetails();
                inteligentOutcomeDetails.setId(Long.valueOf(s));
                inteligentOutcomeDetails.setInfoId(alarmactionInfo.getId());
                inteligentOutcomeDetailsService.update(inteligentOutcomeDetails);
            }
        }
        IntelligentInspectionOutcome intelligentInspectionOutcome = new IntelligentInspectionOutcome();
        intelligentInspectionOutcome.setOutcomeCode(outcomeCode);
        intelligentInspectionOutcome.setState(2);
        intelligentInspectionOutcomeMapper.update(intelligentInspectionOutcome);
        //已读消息
        messageServicel.removeMessge(MessageTypeEnum.MACHINE_INSPECTION.getType(),intelligentInspectionOutcome.getPlanCode());

    }

    @Override
    public AnalyseInspection analyse() {
        return intelligentInspectionOutcomeMapper.analyse();
    }

    @Override
    public TodoAnalyseEntity todoAnalyse(String userName) {
        return intelligentInspectionOutcomeMapper.todoAnalyse(userName);
    }

    @Override
    public List<IntelligentInspectionOutcomeAppVo> findHalfMonth() {
        return intelligentInspectionOutcomeMapper.findHalfMonth();
    }

    @Override
    public List<IntelligentInspectionOutcomeAppVo> findAllErrorByUser(String responsibleName) {
        return intelligentInspectionOutcomeMapper.findAllErrorByUser(responsibleName);
    }

    @Override
    public IntelligentInspectionOutcomeAppVo getInspectionData(String planCode) {
        IntelligentInspectionOutcomeAppVo inspectionData = intelligentInspectionOutcomeMapper.getInspectionData(planCode);
        inspectionData.setIntelligentOutcomeDetailsVos(inteligentOutcomeDetailsService.selectByoutcomeCode(inspectionData.getOutcomeCode()));
        return inspectionData;
    }

    @Override
    public List<InspectionExceptionsVO> queryExceptionToDos() {
        return intelligentInspectionOutcomeMapper.queryExceptionToDos();
    }

    @Override
    public List<OutcomeDispose> getdispose(String outcomeCode) {
        //查询所有处理
        List<AlarmactionInfo> alarmactionInfo = intelligentInspectionOutcomeMapper.getdisposeByoutcomeCode(outcomeCode);
        ArrayList<OutcomeDispose> outcomeDisposes = new ArrayList<>();

        for (AlarmactionInfo info : alarmactionInfo) {
            OutcomeDispose outcomeDispose = new OutcomeDispose();
            outcomeDispose.setRemarks(info.getRemarks());
            outcomeDispose.setSummary(info.getSummary());
            outcomeDispose.setFileList(Arrays.asList(info.getFileList().split(";")));
            outcomeDispose.setFileListBefore(Arrays.asList(info.getFileListBefore().split(";")));
            outcomeDispose.setResolutionReason(info.getResolutionReason());
            outcomeDispose.setDeviceId(info.getDeviceId());
            outcomeDisposes.add(outcomeDispose);
        }
        return outcomeDisposes;
    }

}