package com.zhilianhua.ssicp.task.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.zhilianhua.ssicp.entity.app.MessageTypeEnum;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionOutcomeParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ManualInspectionJob implements Job {
    @Autowired
    private ManualInspectionPlanService manualInspectionPlanService;

    @Autowired
    private ManualInspectionOutcomeService manualInspectionOutcomeService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private DictDataService dictDataService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String id = jobExecutionContext.getJobDetail().getKey().getName();
        ManualInspectionPlan manualInspectionPlan = manualInspectionPlanService.getByid(id);

        //生成实绩
        ManualInspectionOutcome manualInspectionOutcome = new ManualInspectionOutcome();
        ManualInspectionOutcomeParams manualInspectionOutcomeParams = new ManualInspectionOutcomeParams();
        manualInspectionOutcomeParams.setPlanCode(manualInspectionPlan.getPlanCode());
        manualInspectionOutcomeParams.setSize(1000000);
        manualInspectionOutcomeParams.setQueryFlag("deputy");
        manualInspectionOutcome.setPlanName(manualInspectionPlan.getPlanName());//计划名称
        PageResultEntity<ManualInspectionOutcomeVO> servicePage = manualInspectionOutcomeService.findPage(manualInspectionOutcomeParams);
        manualInspectionOutcome.setIterationNumber((int) (servicePage.getTotal() + 1));//第几次巡检
        manualInspectionOutcome.setOutcomeCode(IdUtils.uuid());//实绩编号
        manualInspectionOutcome.setPlanCode(manualInspectionPlan.getPlanCode());////计划编号关联
        manualInspectionOutcome.setState(3);//状态（默认未做：3）
        manualInspectionOutcome.setResponsibleName(manualInspectionPlan.getResponsibleName());//负责人
        //截止时间
        Instant startInstant = new Date().toInstant();
        ZonedDateTime zonedDateTime = startInstant.atZone(ZoneId.systemDefault());
        ZonedDateTime dueDateTime;

        switch (manualInspectionPlan.getCycleUnit()) {
            case "SECOND":
                dueDateTime = zonedDateTime.plusSeconds(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            case "MINUTE":
                dueDateTime = zonedDateTime.plusMinutes(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            case "HOUR":
                dueDateTime = zonedDateTime.plusHours(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            case "DAY":
                dueDateTime = zonedDateTime.plusDays(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            case "WEEK":
                dueDateTime = zonedDateTime.plusWeeks(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            case "MONTH":
                dueDateTime = zonedDateTime.plusMonths(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            case "YEAR":
                dueDateTime = zonedDateTime.plusYears(Long.parseLong(manualInspectionPlan.getCycle()));
                break;
            default:
                throw new IllegalArgumentException("Invalid cycleUnit");
        }

        Date dueDate = Date.from(dueDateTime.toInstant());
        manualInspectionOutcome.setDueDate(dueDate);
        manualInspectionOutcomeService.add(manualInspectionOutcome);

        Map<String, List<String>> map = JSON.parseObject(manualInspectionPlan.getItem(), Map.class);

        List<DictData> dictData = dictDataService.findAll();

        List<String> pids = JSONArray.parseArray(manualInspectionPlan.getPipeIds(), String.class);
        //生成指标项
        Set<ManualOutcomeDetails> manualOutcomeDetails = new HashSet<>();

        //生成判定项
        Set<ManualOutcomeObserve> manualOutcomeObserves = new HashSet<>();

        //生成图片项
        Set<ManualOutcomeImage> manualOutcomeImages = new HashSet<>();


        //仪表指标项
        List<DictData> yibiaodata = dictData.stream().filter(dictData1 -> "instrument_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        //疏水阀指标项
        List<DictData> shushuifadata = dictData.stream().filter(dictData1 -> "valve_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        //阀门指标项
        List<DictData> famendata = dictData.stream().filter(dictData1 -> "steam_traps_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        //管段指标项
        List<DictData> guanduandata = dictData.stream().filter(dictData1 -> "pipe_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        //产汽点指标项
        List<DictData> chanqidiandata = dictData.stream().filter(dictData1 -> "steam_production_point_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        //用汽点指标项
        List<DictData> yongqidiandata = dictData.stream().filter(dictData1 -> "with_steam_spots_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        //补偿器指标项
        List<DictData> buchangqidata = dictData.stream().filter(dictData1 -> "compensator_index".equals(dictData1.getTypeCode())).collect(Collectors.toList());

        for (String pid : pids) {
            Map<String, Object> devices = deviceService.getDevicesBypId(pid);
            //指标项封装
            extracted(manualInspectionOutcome, map, manualOutcomeDetails, yibiaodata,manualOutcomeImages,
                    ((List<DeviceInstrumentVO>) devices.get("yibiao")).stream().map(DeviceInstrumentVO::getId).collect(Collectors.toList()), "instrument_index");
            extracted(manualInspectionOutcome, map, manualOutcomeDetails, famendata, manualOutcomeImages,
                    ((List<DeviceValveVO>) devices.get("famen")).stream().map(DeviceValveVO::getId).collect(Collectors.toList()), "steam_traps_index");
            extracted(manualInspectionOutcome, map, manualOutcomeDetails, shushuifadata, manualOutcomeImages,
                    ((List<DeviceSteamTrapsVO>) devices.get("shushuifa")).stream().map(DeviceSteamTrapsVO::getId).collect(Collectors.toList()), "valve_index");
            extracted(manualInspectionOutcome, map, manualOutcomeDetails, chanqidiandata, manualOutcomeImages,
                    ((List<DeviceGasPointVO>) devices.get("chanqidian")).stream().map(DeviceGasPointVO::getId).collect(Collectors.toList()), "steam_production_point_index");
            extracted(manualInspectionOutcome, map, manualOutcomeDetails, yongqidiandata, manualOutcomeImages,
                    ((List<DeviceGasPointVO>) devices.get("yongqidian")).stream().map(DeviceGasPointVO::getId).collect(Collectors.toList()), "with_steam_spots_index");
            extracted(manualInspectionOutcome, map, manualOutcomeDetails, buchangqidata, manualOutcomeImages,
                    ((List<DeviceCompensatorVO>) devices.get("buchangqi")).stream().map(DeviceCompensatorVO::getId).collect(Collectors.toList()), "compensator_index");

            for (Map.Entry<String, List<String>> stringListEntry : map.entrySet().stream().filter(entry -> "pipe_index".equals(entry.getKey())).collect(Collectors.toList())) {
                for (String s : stringListEntry.getValue()) {
                    ManualOutcomeDetails manualOutcomeDetail = new ManualOutcomeDetails();
                    manualOutcomeDetail.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
                    manualOutcomeDetail.setIndex(s);
                    manualOutcomeDetail.setUnit(guanduandata.stream().filter(dictData1 -> dictData1.getDictValue().equals(s)).findFirst().get().getExpand1());
                    manualOutcomeDetail.setDeviceId(pid);
                    manualOutcomeDetails.add(manualOutcomeDetail);
                }
            }


            //判定项封装
            extracted1(manualInspectionOutcome, map, dictData, manualOutcomeObserves, manualOutcomeImages,
                    ((List<DeviceInstrumentVO>) devices.get("yibiao")).stream().map(DeviceInstrumentVO::getId).collect(Collectors.toList()), "instrument_decide");
            extracted1(manualInspectionOutcome, map, dictData, manualOutcomeObserves, manualOutcomeImages,
                    ((List<DeviceValveVO>) devices.get("famen")).stream().map(DeviceValveVO::getId).collect(Collectors.toList()), "valve__decide");
            extracted1(manualInspectionOutcome, map, dictData, manualOutcomeObserves, manualOutcomeImages,
                    ((List<DeviceSteamTrapsVO>) devices.get("shushuifa")).stream().map(DeviceSteamTrapsVO::getId).collect(Collectors.toList()), "steam_traps_decide");
            extracted1(manualInspectionOutcome, map, dictData, manualOutcomeObserves, manualOutcomeImages,
                    ((List<DeviceGasPointVO>) devices.get("chanqidian")).stream().map(DeviceGasPointVO::getId).collect(Collectors.toList()), "steam_production_point_decide");
            extracted1(manualInspectionOutcome, map, dictData, manualOutcomeObserves, manualOutcomeImages,
                    ((List<DeviceGasPointVO>) devices.get("yongqidian")).stream().map(DeviceGasPointVO::getId).collect(Collectors.toList()), "with_steam_spots_decide");
            extracted1(manualInspectionOutcome, map, dictData, manualOutcomeObserves, manualOutcomeImages,
                    ((List<DeviceCompensatorVO>) devices.get("buchangqi")).stream().map(DeviceCompensatorVO::getId).collect(Collectors.toList()), "compensator_decide");

            for (Map.Entry<String, List<String>> stringListEntry : map.entrySet().stream().filter(entry -> "pipe_decide".equals(entry.getKey())).collect(Collectors.toList())) {
                for (String s : stringListEntry.getValue()) {
                    ManualOutcomeObserve manualOutcomeObserve = new ManualOutcomeObserve();
                    manualOutcomeObserve.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
                    manualOutcomeObserve.setDecideDescription(dictData.stream().filter(dictData1 -> "pipe_decide".equals(dictData1.getTypeCode()) && s.equals(dictData1.getDictValue())).findFirst().get().getDictLabel());
                    manualOutcomeObserve.setDeviceId(pid);
                    manualOutcomeObserves.add(manualOutcomeObserve);
                }
            }
            ManualOutcomeImage manualOutcomeImage = new ManualOutcomeImage();
            manualOutcomeImage.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
            manualOutcomeImage.setDeviceId(pid);
            manualOutcomeImages.add(manualOutcomeImage);
        }

        //生成消息
        messageService.addManualInspection(manualInspectionOutcome);
        List<ManualOutcomeObserve> objects1 = new ArrayList<>();
        objects1.addAll(manualOutcomeObserves);
        manualInspectionOutcomeService.addObserve(objects1);
        List<ManualOutcomeImage> objects2 = new ArrayList<>();
        objects2.addAll(manualOutcomeImages);
        manualInspectionOutcomeService.addImages(objects2);
        List<ManualOutcomeDetails> objects3 = new ArrayList<>();
        objects3.addAll(manualOutcomeDetails);
        manualInspectionOutcomeService.addDetails(objects3);
    }

    private static void extracted1(ManualInspectionOutcome manualInspectionOutcome, Map<String, List<String>> map, List<DictData> dictData, Set<ManualOutcomeObserve> manualOutcomeObserves, Set<ManualOutcomeImage> manualOutcomeImages, List<String> devicesids, String decide) {
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet().stream().filter(entry -> decide.equals(entry.getKey())).collect(Collectors.toList())) {
            for (String s : stringListEntry.getValue()) {
                for (String devicesid : devicesids) {
                    ManualOutcomeImage manualOutcomeImage = new ManualOutcomeImage();
                    manualOutcomeImage.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
                    manualOutcomeImage.setDeviceId(devicesid);
                    manualOutcomeImages.add(manualOutcomeImage);
                    ManualOutcomeObserve manualOutcomeObserve = new ManualOutcomeObserve();
                    manualOutcomeObserve.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
                    manualOutcomeObserve.setDecideDescription(dictData.stream().filter(dictData1 -> decide.equals(dictData1.getTypeCode()) && s.equals(dictData1.getDictValue())).findFirst().get().getDictLabel());
                    manualOutcomeObserve.setDeviceId(devicesid);
                    manualOutcomeObserves.add(manualOutcomeObserve);
                }
            }
        }
    }

    private static void extracted(ManualInspectionOutcome manualInspectionOutcome, Map<String, List<String>> map, Set<ManualOutcomeDetails> manualOutcomeDetails, List<DictData> yibiaodata, Set<ManualOutcomeImage> manualOutcomeImages, List<String> devicesids, String index) {
        for (Map.Entry<String, List<String>> stringListEntry : map.entrySet().stream().filter(entry -> index.equals(entry.getKey())).collect(Collectors.toList())) {
            for (String s : stringListEntry.getValue()) {
                for (String devicesid : devicesids) {
                    ManualOutcomeImage manualOutcomeImage = new ManualOutcomeImage();
                    manualOutcomeImage.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
                    manualOutcomeImage.setDeviceId(devicesid);
                    manualOutcomeImages.add(manualOutcomeImage);
                    ManualOutcomeDetails manualOutcomeDetail = new ManualOutcomeDetails();
                    manualOutcomeDetail.setOutcomeCode(manualInspectionOutcome.getOutcomeCode());
                    manualOutcomeDetail.setIndex(s);
                    Optional<DictData> dictData = yibiaodata.stream().filter(dictData1 -> dictData1.getDictValue().equals(s)).findFirst();
                    dictData.ifPresent(data -> manualOutcomeDetail.setUnit(data.getExpand1()));
                    manualOutcomeDetail.setDeviceId(devicesid);
                    manualOutcomeDetails.add(manualOutcomeDetail);
                }
            }
        }
    }

}