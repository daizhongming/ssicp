package com.zhilianhua.ssicp.task.impl;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.db.InteligentOutcomeDetails;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionOutcome;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class IntelligentInspectionJob implements Job {
    @Autowired
    private IntelligentInspectionPlanService intelligentInspectionPlanService;

    @Autowired
    private IntelligentInspectionOutcomeService intelligentInspectionOutcomeService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private DeviceAlarmService deviceAlarmService;

    @Autowired
    private InteligentOutcomeDetailsService inteligentOutcomeDetailsService;

    @Autowired
    private DeviceIndexLogService deviceIndexLogService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String id = jobExecutionContext.getJobDetail().getKey().getName();
        IntelligentInspectionOutcome intelligentInspectionOutcome = new IntelligentInspectionOutcome();
        intelligentInspectionOutcome.setOutcomeCode(IdUtils.uuid());//实绩编号
        intelligentInspectionOutcome.setState(0);//实绩状态
        IntelligentInspectionPlan inspectionPlan = intelligentInspectionPlanService.findByid(id);
        //获取模板
        Map<String, List<String>> map = JSON.parseObject(inspectionPlan.getItem(), Map.class);

        //获取管段信息
        List<String> pids = JSON.parseArray(inspectionPlan.getPipeIds(), String.class);
        List<DeviceIndexLog> last = deviceIndexLogService.findLast();
        for (String pid : pids) {
            Map<String, Object> devices = deviceService.getDevicesBypId(pid);

            List<Map.Entry<String, List<String>>> collect5 = map.entrySet().stream().filter(entry -> "instrument_index".equals(entry.getKey())).collect(Collectors.toList());
            List<DeviceInstrumentVO> yibiao = (List<DeviceInstrumentVO>) devices.get("yibiao");
            extracted(intelligentInspectionOutcome, last, collect5, yibiao.stream().map(DeviceInstrumentVO::getId).collect(Collectors.toList()));

            List<Map.Entry<String, List<String>>> collect4 = map.entrySet().stream().filter(entry -> "steam_traps_index".equals(entry.getKey())).collect(Collectors.toList());
            List<DeviceValveVO> famen = (List<DeviceValveVO>) devices.get("famen");
            extracted(intelligentInspectionOutcome, last, collect4, famen.stream().map(DeviceValveVO::getId).collect(Collectors.toList()));

            List<Map.Entry<String, List<String>>> collect3 = map.entrySet().stream().filter(entry -> "valve_index".equals(entry.getKey())).collect(Collectors.toList());
            List<DeviceSteamTrapsVO> shushuifa = (List<DeviceSteamTrapsVO>) devices.get("shushuifa");
            extracted(intelligentInspectionOutcome, last, collect3, shushuifa.stream().map(DeviceSteamTrapsVO::getId).collect(Collectors.toList()));

            List<DeviceGasPointVO> chanqidian = (List<DeviceGasPointVO>) devices.get("chanqidian");
            List<Map.Entry<String, List<String>>> collect2 = map.entrySet().stream().filter(entry -> "steam_production_point_index".equals(entry.getKey())).collect(Collectors.toList());
            extracted(intelligentInspectionOutcome, last, collect2, chanqidian.stream().map(DeviceGasPointVO::getId).collect(Collectors.toList()));


            List<DeviceGasPointVO> yongqidian = (List<DeviceGasPointVO>) devices.get("yongqidian");
            List<Map.Entry<String, List<String>>> collect1 = map.entrySet().stream().filter(entry -> "with_steam_spots_index".equals(entry.getKey())).collect(Collectors.toList());
            extracted(intelligentInspectionOutcome, last, collect1, yongqidian.stream().map(DeviceGasPointVO::getId).collect(Collectors.toList()));

            List<DeviceCompensatorVO> buchangqi = (List<DeviceCompensatorVO>) devices.get("buchangqi");
            List<Map.Entry<String, List<String>>> collect = map.entrySet().stream().filter(entry -> "compensator_index".equals(entry.getKey())).collect(Collectors.toList());
            extracted(intelligentInspectionOutcome, last, collect, buchangqi.stream().map(DeviceCompensatorVO::getId).collect(Collectors.toList()));

            List<Map.Entry<String, List<String>>> entryList = map.entrySet().stream().filter(entry -> "pipe_index".equals(entry.getKey())).collect(Collectors.toList());
            extracted(intelligentInspectionOutcome, last, entryList, Collections.singletonList(pid));
        }

        //修改计划
        inspectionPlan.setIterationNumber(inspectionPlan.getIterationNumber() + 1);
        intelligentInspectionPlanService.update(inspectionPlan);

        //封装实绩
        intelligentInspectionOutcome.setPlanCode(id);//关联计划编号
        intelligentInspectionOutcome.setPlanName(inspectionPlan.getPlanName());
        intelligentInspectionOutcome.setIterationNumber(inspectionPlan.getIterationNumber());
        intelligentInspectionOutcome.setResponsibleName(inspectionPlan.getResponsibleName());
        intelligentInspectionOutcomeService.insert(intelligentInspectionOutcome);

        //生成消息
        if (intelligentInspectionOutcome.getState() == 1) {
            messageService.addIntelligentInspectionError(inspectionPlan);
        }
    }

    private void extracted(IntelligentInspectionOutcome intelligentInspectionOutcome, List<DeviceIndexLog> last, List<Map.Entry<String, List<String>>> collect, List<String> ids) {
        for (Map.Entry<String, List<String>> stringListEntry : collect) {
            for (String s : stringListEntry.getValue()) {
                for (String deviceid : ids) {
                    Optional<DeviceIndexLog> optionalIndexLog = last.stream().filter(deviceIndexLog -> deviceid.equals(deviceIndexLog.getDeviceId()) && deviceIndexLog.getIndexId().equals(s)).findFirst();
                    if (optionalIndexLog.isPresent()) {
                        DeviceIndexLog indexLog = optionalIndexLog.get();
                        InteligentOutcomeDetails inteligentOutcomeDetails = new InteligentOutcomeDetails();
                        inteligentOutcomeDetails.setOutcomeCode(intelligentInspectionOutcome.getOutcomeCode());
                        inteligentOutcomeDetails.setIndexValue(indexLog.getValue());
                        inteligentOutcomeDetails.setIndex(indexLog.getIndexId());
                        inteligentOutcomeDetails.setDeviceId(deviceid);
                        inteligentOutcomeDetails.setUnit(indexLog.getUnit());
                        DeviceAlarm deviceAlarm = deviceAlarmService.findAlarBydidAndindex(s, deviceid);
                        if (deviceAlarm != null) {
                            inteligentOutcomeDetails.setAlarmLevel(deviceAlarm.getAlarmLevel(indexLog.getValue()));
                            if (inteligentOutcomeDetails.getAlarmLevel() != 3) {
                                intelligentInspectionOutcome.setState(1);//实绩状态
                            }
                        }
                        inteligentOutcomeDetailsService.insert(inteligentOutcomeDetails);
                    }
                }
            }
        }
    }
}
