package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.app.MessageTypeEnum;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.request.OutcomeDispose;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionOutcomeParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.ManualInspectionOutcomeMapper;
import com.zhilianhua.ssicp.service.AlarmactionInfoService;
import com.zhilianhua.ssicp.service.DeviceAlarmService;
import com.zhilianhua.ssicp.service.ManualInspectionOutcomeService;
import com.zhilianhua.ssicp.service.MessageService;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ManualInspectionOutcomeServiceImpl implements ManualInspectionOutcomeService {
    @Autowired
    private ManualInspectionOutcomeMapper manualInspectionOutcomeMapper;

    @Autowired
    private AlarmactionInfoService alarmactionInfoService;

    @Autowired
    private DeviceAlarmService deviceAlarmService;

    @Autowired
    private MessageService messageService;

    @Override
    public PageResultEntity<ManualInspectionOutcomeVO> findPage(ManualInspectionOutcomeParams manualInspectionOutcomeParams) {
        if ("1".equals(manualInspectionOutcomeParams.getOutcomeType())) {
            manualInspectionOutcomeParams.setStatus(Collections.singletonList("0"));
        } else if ("2".equals(manualInspectionOutcomeParams.getOutcomeType())) {
            if ("1".equals(manualInspectionOutcomeParams.getActionStatus())) {
                manualInspectionOutcomeParams.setStatus(Collections.singletonList("1"));
            } else if ("2".equals(manualInspectionOutcomeParams.getActionStatus())) {
                manualInspectionOutcomeParams.setStatus(Collections.singletonList("2"));
            } else {
                manualInspectionOutcomeParams.setStatus(Stream.of("1", "2").collect(Collectors.toList()));
            }
        } else if ("3".equals(manualInspectionOutcomeParams.getOutcomeType())) {
            manualInspectionOutcomeParams.setStatus(Collections.singletonList("3"));
        }
        if ("deputy".equals(manualInspectionOutcomeParams.getQueryFlag())) {
            return manualInspectionOutcomeMapper.searchDeputy(manualInspectionOutcomeParams);
        }
        return manualInspectionOutcomeMapper.search(manualInspectionOutcomeParams);
    }

    @Override
    public Map<String, Map<String, ManualOutcomeDetailsVO>> load(String outcomeCode) {
        //查询指标项
        List<ManualOutcomeDetails> manualOutcomeDetails = manualInspectionOutcomeMapper.detailsByoutcomeCode(outcomeCode);
        //查询判定项
        List<ManualOutcomeObserve> manualOutcomeObserves = manualInspectionOutcomeMapper.observeByoutcomeCode(outcomeCode);
        //查询照片
        List<ManualOutcomeImage> manualOutcomeImages = manualInspectionOutcomeMapper.imageByoutcomeCode(outcomeCode);
        // 对 ManualOutcomeDetails 进行分组
        Map<String, Map<String, List<ManualOutcomeDetails>>> groupedDetails = manualOutcomeDetails.stream()
                .collect(Collectors.groupingBy(ManualOutcomeDetails::getParentDeviceType,
                        Collectors.groupingBy(ManualOutcomeDetails::getDeviceId)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream().collect(Collectors.toMap(
                                subEntry -> subEntry.getValue().get(0).getDeviceName(),
                                Map.Entry::getValue,
                                (existing, replacement) -> existing // 在这里处理冲突，保留现有的条目
                        ))
                ));

// 对 ManualOutcomeObserve 进行分组
        Map<String, Map<String, List<ManualOutcomeObserve>>> groupedObserves = manualOutcomeObserves.stream()
                .collect(Collectors.groupingBy(ManualOutcomeObserve::getParentDeviceType,
                        Collectors.groupingBy(ManualOutcomeObserve::getDeviceId)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream().collect(Collectors.toMap(
                                subEntry -> subEntry.getValue().get(0).getDeviceName(),
                                Map.Entry::getValue,
                                (existing, replacement) -> existing // 在这里处理冲突，保留现有的条目

                        ))
                ));

// 对 ManualOutcomeImage 进行分组
        Map<String, Map<String, List<ManualOutcomeImage>>> groupedImages = manualOutcomeImages.stream()
                .collect(Collectors.groupingBy(ManualOutcomeImage::getParentDeviceType,
                        Collectors.groupingBy(ManualOutcomeImage::getDeviceId)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().entrySet().stream().collect(Collectors.toMap(
                                subEntry -> subEntry.getValue().get(0).getDeviceName(),
                                Map.Entry::getValue,
                                (existing, replacement) -> existing // 在这里处理冲突，保留现有的条目
                        ))
                ));

        Map<String, Map<String, ManualOutcomeDetailsVO>> result = new HashMap<>();

        groupedDetails.forEach((parentDeviceType, deviceDetailsMap) -> {
            deviceDetailsMap.forEach((deviceId, detailsList) -> {
                ManualOutcomeDetailsVO vo = new ManualOutcomeDetailsVO();
                vo.setManualOutcomeDetails(detailsList);
                vo.setManualOutcomeObserves(groupedObserves.getOrDefault(parentDeviceType, Collections.emptyMap()).get(deviceId));
                vo.setManualOutcomeImages(groupedImages.getOrDefault(parentDeviceType, Collections.emptyMap()).get(deviceId));

                result.computeIfAbsent(parentDeviceType, k -> new HashMap<>()).put(deviceId, vo);
            });
        });
        return result;
    }

    @Override
    public void dispose(List<OutcomeDispose> outcomeDisposes, String outcomeCode) {
        AlarmactionInfo alarmactionInfo = new AlarmactionInfo();
        for (OutcomeDispose outcomeDispose : outcomeDisposes) {
            alarmactionInfo.setId(IdUtils.uuid());
            alarmactionInfo.setSummary(outcomeDispose.getSummary());
            alarmactionInfo.setResolutionReason(outcomeDispose.getResolutionReason());
            alarmactionInfo.setRemarks(outcomeDispose.getRemarks());
            alarmactionInfo.setFileList(String.join(";", outcomeDispose.getFileList()));
            alarmactionInfo.setFileListBefore(String.join(";", outcomeDispose.getFileListBefore()));
            alarmactionInfoService.add(alarmactionInfo);
            //修改收集项
            for (String s : outcomeDispose.getDetailsId()) {
                ManualOutcomeDetails manualOutcomeDetails = new ManualOutcomeDetails();
                manualOutcomeDetails.setId(s);
                manualOutcomeDetails.setInfoId(alarmactionInfo.getId());
                manualInspectionOutcomeMapper.updateDispose(manualOutcomeDetails);
            }

            //修改判定项
            for (String s : outcomeDispose.getObservesId()) {
                ManualOutcomeObserve manualOutcomeObserve = new ManualOutcomeObserve();
                manualOutcomeObserve.setId(s);
                manualOutcomeObserve.setInfoId(alarmactionInfo.getId());
                manualInspectionOutcomeMapper.updateObserve(manualOutcomeObserve);
            }

            //修改图片
            for (String s : outcomeDispose.getImagesId()) {
                ManualOutcomeImage manualOutcomeImage = new ManualOutcomeImage();
                manualOutcomeImage.setId(s);
                manualOutcomeImage.setInfoId(alarmactionInfo.getId());
                manualInspectionOutcomeMapper.updateImage(manualOutcomeImage);
            }
        }
        //修改巡检实绩
        ManualInspectionOutcome manualInspectionOutcome = new ManualInspectionOutcome();
        manualInspectionOutcome.setOutcomeCode(outcomeCode);
        manualInspectionOutcome.setState(2);
        manualInspectionOutcomeMapper.update(manualInspectionOutcome);

        //已读消息
        messageService.removeMessge(MessageTypeEnum.MANUAL_INSPECTION.getType(), manualInspectionOutcome.getPlanName());
    }

    @Override
    public AnalyseManualInspection analyse(Date from, Date to) {
        return manualInspectionOutcomeMapper.analyse(from, to);
    }

    @Override
    public List<AnalyseInspection> analyseByType() {
        return manualInspectionOutcomeMapper.analyseByType();
    }

    @Override
    public TodoAnalyseEntity todoAnalyseForInspection(String userName) {
        return manualInspectionOutcomeMapper.todoAnalyseForInspection(userName);
    }

    @Override
    public TodoAnalyseEntity todoAnalyseForError(String userName) {
        return manualInspectionOutcomeMapper.todoAnalyseForError(userName);
    }

    @Override
    public void add(ManualInspectionOutcome manualInspectionOutcome) {
        manualInspectionOutcomeMapper.add(manualInspectionOutcome);
    }

    @Override
    public void addImages(List<ManualOutcomeImage> manualOutcomeImages) {
        if (manualOutcomeImages != null && manualOutcomeImages.size() > 0) {
            manualInspectionOutcomeMapper.addImages(manualOutcomeImages);
        }
    }

    @Override
    public void addObserve(List<ManualOutcomeObserve> manualOutcomeObserves) {
        if (manualOutcomeObserves != null && manualOutcomeObserves.size() > 0) {
            manualInspectionOutcomeMapper.addObserve(manualOutcomeObserves);
        }

    }

    @Override
    public void addDetails(List<ManualOutcomeDetails> manualOutcomeDetails) {
        if (manualOutcomeDetails != null && manualOutcomeDetails.size() > 0) {
            manualInspectionOutcomeMapper.addDetails(manualOutcomeDetails);
        }
    }


    @Override
    public List<ManualInspectionOutcomeAppVo> findHalfMonth() {
        return manualInspectionOutcomeMapper.findHalfMonth();
    }

    @Override
    public List<ManualInspectionOutcomeAppVo> findAllByUser(String responsibleName) {
        return manualInspectionOutcomeMapper.findAllByUser(responsibleName);
    }

    @Override
    public ManualInspectionOutcomeAppVo getManualInspectionData(String planCode) {
        ManualInspectionOutcomeAppVo manualInspectionData = manualInspectionOutcomeMapper.getManualInspectionData(planCode);
        //查询判定项
        manualInspectionData.setManualOutcomeObserves(manualInspectionOutcomeMapper.observeByoutcomeCode(manualInspectionData.getOutcomeCode()));
        //查询指标项
        manualInspectionData.setManualOutcomeDetails(manualInspectionOutcomeMapper.detailsByoutcomeCode(manualInspectionData.getOutcomeCode()));
        //查询照片
        manualInspectionData.setManualOutcomeImages(manualInspectionOutcomeMapper.imageByoutcomeCode(manualInspectionData.getOutcomeCode()));
        return manualInspectionData;
    }

    @Override
    public List<InspectionExceptionsVO> queryExceptionToDos() {
        return manualInspectionOutcomeMapper.queryExceptionToDos();
    }

    @Override
    public List<ManualOutcomeObserve> observeByoutcomeCode(String outcomeCode) {
        return manualInspectionOutcomeMapper.observeByoutcomeCode(outcomeCode);
    }

    @Override
    public List<ManualOutcomeDetails> detailsByoutcomeCode(String outcomeCode) {
        return manualInspectionOutcomeMapper.detailsByoutcomeCode(outcomeCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disposeWrite(ManualInspectionOutcomeAdd manualInspectionOutcomeAdd) {
        ManualInspectionOutcome manualInspectionOutcome = new ManualInspectionOutcome();
        manualInspectionOutcome.setState(0);
        manualInspectionOutcome.setOutcomeCode(manualInspectionOutcomeAdd.getOutcomeCode());
        manualInspectionOutcome.setCreateTime(new Date());
        List<DeviceAlarm> deviceAlarms = deviceAlarmService.findAll();
        //指标项集合
        for (ManualInspectionOutcomeAdd.ManualOutcomeDetailsAdd manualOutcomeDetailAdd : manualInspectionOutcomeAdd.getManualOutcomeDetails()) {
            ManualOutcomeDetails manualOutcomeDetails = new ManualOutcomeDetails();
            manualOutcomeDetails.setId(manualOutcomeDetailAdd.getId());
            manualOutcomeDetails.setIndexValue(manualOutcomeDetailAdd.getIndexValue());
            deviceAlarms.stream().filter(deviceAlarm -> deviceAlarm.getIndex().equals(manualOutcomeDetailAdd.getIndex()) && deviceAlarm.getDeviceId().equals(manualOutcomeDetailAdd.getDeviceId())).findFirst().ifPresent(deviceAlarm -> {
                Integer alarmLevel = deviceAlarm.getAlarmLevel(manualOutcomeDetailAdd.getIndexValue());
                if (Objects.nonNull(alarmLevel)) {
                    // 有报警
                    manualInspectionOutcome.setState(1);
                }
                manualOutcomeDetails.setAlarmLevel(Objects.isNull(alarmLevel) ? 3 : alarmLevel.intValue());
            });
            manualInspectionOutcomeMapper.updateDispose(manualOutcomeDetails);
        }

        //判定项集合
        for (ManualInspectionOutcomeAdd.ManualOutcomeObserveAdd manualOutcomeObserveAdd : manualInspectionOutcomeAdd.getManualOutcomeObserves()) {
            ManualOutcomeObserve manualOutcomeObserve = new ManualOutcomeObserve();
            manualOutcomeObserve.setDecide(manualOutcomeObserveAdd.getDecide());
            manualOutcomeObserve.setId(manualOutcomeObserveAdd.getId());
            if (manualOutcomeObserve.getDecide().intValue() == 1) {
                // 有异常
                manualInspectionOutcome.setState(1);
            }
            manualInspectionOutcomeMapper.updateObserve(manualOutcomeObserve);
        }

        //图片
        for (ManualInspectionOutcomeAdd.ManualOutcomeImageAdd manualOutcomeImageAdd : manualInspectionOutcomeAdd.getManualOutcomeImages()) {
            ManualOutcomeImage manualOutcomeImage = new ManualOutcomeImage();
            manualOutcomeImage.setId(manualOutcomeImageAdd.getId());
            manualOutcomeImage.setFileList(manualOutcomeImageAdd.getFileList());
            manualInspectionOutcomeMapper.updateImage(manualOutcomeImage);
        }

        //已读消息
        messageService.removeMessge(MessageTypeEnum.MANUAL_INSPECTION.getType(), manualInspectionOutcome.getPlanName());

        //生成消息
        if (manualInspectionOutcome.getState().intValue() == 1) {
            messageService.addManualInspectionError(manualInspectionOutcomeAdd);
        }
        manualInspectionOutcomeMapper.update(manualInspectionOutcome);
    }

    @Override
    public List<DeviceIndexLog> findIndexLogsHistory(String deviceId, String index) {
        List<DeviceIndexLog> indexLogsHistory = manualInspectionOutcomeMapper.findIndexLogsHistory(deviceId, index);
        return indexLogsHistory;
    }

    @Override
    public List<OutcomeDispose> getdispose(String outcomeCode) {
        List<AlarmactionInfo> alarmactionInfos = manualInspectionOutcomeMapper.getdispose(outcomeCode);
        List<OutcomeDispose> outcomeDisposes = new ArrayList<>();
        for (AlarmactionInfo info : alarmactionInfos) {
            OutcomeDispose outcomeDispose = new OutcomeDispose();
            outcomeDispose.setRemarks(info.getRemarks());
            outcomeDispose.setSummary(info.getSummary());
            outcomeDispose.setFileList(Arrays.asList(info.getFileList().split(";")));
            outcomeDispose.setFileListBefore(Arrays.asList(info.getFileListBefore().split(";")));
            outcomeDispose.setResolutionReason(info.getResolutionReason());
            outcomeDispose.setDeviceName(info.getDeviceName());
            outcomeDisposes.add(outcomeDispose);
        }
        return outcomeDisposes;
    }
}