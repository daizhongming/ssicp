package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.app.TodoTypeEnum;
import com.zhilianhua.ssicp.entity.app.TodosVO;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.request.app.ReadMessageParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.DeviceIndexLogVo;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private DeviceAlarmLogService deviceAlarmLogService;
    @Autowired
    private DeviceAlarmService deviceAlarmService;
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private IntelligentInspectionOutcomeService intelligentInspectionOutcomeService;
    @Autowired
    private ManualInspectionOutcomeService manualInspectionOutcomeService;

    @Autowired
    private ManualInspectionPlanService manualInspectionPlanService;
    @Autowired
    private SteamIndexLogService steamIndexLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private DeviceIndexLogService deviceIndexLogService;

    @Override
    public List<TodosVO> todos(String userName) {
        List<TodosVO> result = new ArrayList();

        //查询报警
        if (userService.isInspector(userName)) {
            DeviceAlarmLogSearchParams pageParams = new DeviceAlarmLogSearchParams();
            pageParams.setSize(10000);
            pageParams.setHandleStatus(0);
            deviceAlarmLogService.search(pageParams).getData().forEach(deviceAlarmLogVo -> {
                TodosVO todosVO = new TodosVO();
                todosVO.setType(TodoTypeEnum.ALARM.getType());
                todosVO.setExtra(deviceAlarmLogVo);
                todosVO.setTime(deviceAlarmLogVo.getStartTime());
                result.add(todosVO);
            });
        }
        //查询智能巡检实绩（异常）
        intelligentInspectionOutcomeService.findAllErrorByUser(userName).forEach(intelligentInspectionOutcomeVO -> {
            TodosVO todosVO = new TodosVO();
            todosVO.setType(TodoTypeEnum.MACHINE_INSPECTION_ERROR.getType());
            todosVO.setExtra(intelligentInspectionOutcomeVO);
            todosVO.setTime(intelligentInspectionOutcomeVO.getCreateTime());
            result.add(todosVO);
        });

        //查询人工巡检计划
        //整合todosVO集合
        manualInspectionOutcomeService.findAllByUser(userName).forEach(manualInspectionOutcome -> {
            TodosVO todosVO = new TodosVO();
            todosVO.setExtra(manualInspectionOutcome);
            switch (manualInspectionOutcome.getState()) {
                case 3:
                    // 未做，未过期
                    todosVO.setType(TodoTypeEnum.MANUAL_INSPECTION.getType());
                    todosVO.setTime(manualInspectionOutcome.getCreateTime());
                    break;
                case 0:
                    // 数据正常
                    return;
                case 1:
                    // 异常未处理
                    todosVO.setType(TodoTypeEnum.MANUAL_INSPECTION_ERROR.getType());
                    todosVO.setTime(manualInspectionOutcome.getCreateTime());
                    break;
                case 2:
                    // 异常已处理
                    return;
                default:
                    return;
            }
            result.add(todosVO);
        });
        result.sort((i1, i2) -> i2.getTime().compareTo(i1.getTime()));
        //合并todosVO
        return result;
    }


    @Override
    public List<TodoAnalyseEntity> todoAnalyse(String userName) {
        boolean inspector = userService.isInspector(userName);
        List<TodoAnalyseEntity> result = new ArrayList();
        // 报警
        TodoAnalyseEntity alarmTodoAnalyseEntity = deviceAlarmLogService.analyse();
        alarmTodoAnalyseEntity.setType("alarm");
        result.add(alarmTodoAnalyseEntity);

        // 智能巡检  异常
        TodoAnalyseEntity machineInspectionTodoAnalyseEntity = intelligentInspectionOutcomeService.todoAnalyse(inspector ? userName : null);
        machineInspectionTodoAnalyseEntity.setType("error");
        // 人工巡检 异常+巡检
        TodoAnalyseEntity manualInspectionTodoAnalyseEntityForInspection = manualInspectionOutcomeService.todoAnalyseForError(inspector ? userName : null);
        machineInspectionTodoAnalyseEntity.setTotal(machineInspectionTodoAnalyseEntity.getTotal() + manualInspectionTodoAnalyseEntityForInspection.getTotal());
        machineInspectionTodoAnalyseEntity.setHandled(machineInspectionTodoAnalyseEntity.getHandled() + manualInspectionTodoAnalyseEntityForInspection.getHandled());
        machineInspectionTodoAnalyseEntity.setUnHandle(machineInspectionTodoAnalyseEntity.getUnHandle() + manualInspectionTodoAnalyseEntityForInspection.getUnHandle());
        result.add(machineInspectionTodoAnalyseEntity);

        TodoAnalyseEntity manualInspectionTodoAnalyseEntityForError = manualInspectionOutcomeService.todoAnalyseForInspection(inspector ? userName : null);
        manualInspectionTodoAnalyseEntityForError.setType("inspection");
        result.add(manualInspectionTodoAnalyseEntityForError);
        return result;
    }

    @Override
    public void readMessage(ReadMessageParams readMessageParams) {
        if (readMessageParams.getAllRead().intValue() == 1) {
            // 消息某个用户的全部消息
            messageService.readAll(readMessageParams.getUserName());
        } else {
            // 读取某一个消息
            messageService.readById(readMessageParams.getMessageId());
        }
    }

    @Override
    public List<SteamIndexLog> productionAndConsumption() {
        return steamIndexLogService.findToday();
    }

    @Override
    public User findByName(String userName) {
        if (StringUtils.isBlank(userName)) return null;
        return userService.findByName(userName);
    }

    @Override
    public List<FileInfo> uploadMulti(List<MultipartFile> collect) {
        return fileService.uploadMulti(collect);
    }

    @Override
    public List<Message> messages(String userName) {
        return messageService.findByUser(userName);
    }

    @Override
    public List<MessageAnalyseEntity> messagesAnalyse(String userName) {
        if (userService.isInspector(userName)) {
            return messageService.analyse(userName);
        } else {
            return messageService.analyse(null);
        }
    }

    @Override
    public List<AnalyseEntity> indexAlarmAnalyse() {
        return deviceAlarmLogService.indexAlarmAnalyse();
    }

    @Override
    public List<AnalyseEntity> deviceAlarmAnalyse() {
        return deviceAlarmLogService.deviceAlarmAnalyse();
    }

    @Override
    public List<AnalyseEntity> deviceCountAnalyse() {
        return deviceService.deviceTypeCountAnalyse();
    }

    @Override
    public List<DeviceIndexLogVo> deviceIndexLogs() {
        Map<String, Device> deviceIdAndDeviceMap = deviceService.findAll().stream().collect(Collectors.toMap(Device::getId, item -> item));
        Map<String, DeviceAlarm> deviceAlarmKeyAndDeviceAlarmMap = deviceAlarmService.findAll()
                .stream().collect(Collectors.toMap(deviceAlarm -> deviceAlarm.getDeviceId() + "_" + deviceAlarm.getIndex(), item -> item));
        return deviceIndexLogService.findLast().stream().map(deviceIndexLog -> {
//            DeviceAlarmVo deviceAlarmVo = deviceAlarmService.getById(deviceIndexLog.getIndexId());
//            if (Objects.isNull(deviceAlarmVo)) return null;
            DeviceIndexLogVo deviceIndexLogVo = new DeviceIndexLogVo();
            deviceIndexLogVo.setIndexId(deviceIndexLog.getIndexId());
            deviceIndexLogVo.setDeviceId(deviceIndexLog.getDeviceId());
            deviceIndexLogVo.setUnit(deviceIndexLog.getUnit());
            deviceIndexLogVo.setIndexName(deviceIndexLog.getIndexId());
            deviceIndexLogVo.setIndexValue(deviceIndexLog.getValue());
            Device device = deviceIdAndDeviceMap.get(deviceIndexLog.getDeviceId());
            if (Objects.isNull(device)) return null;
            Optional<String> deviceTypeName = DeviceTypeEnum.getNameByParentDeviceType(device.getParentDeviceType());
            if (!deviceTypeName.isPresent()) return null;
            deviceIndexLogVo.setDeviceName(device.getDeviceName());
            deviceIndexLogVo.setDeviceType(deviceTypeName.get());
            deviceIndexLogVo.setCreateTime(Date.from(deviceIndexLog.getTime()));
//            deviceIndexLogVo.setAlarmFlag(deviceAlarmVo.isAlarm(deviceIndexLog.getValue()) ? 1 : 0);
//            deviceIndexLogVo.setAlarmLevel(deviceAlarmVo.getAlarmLevel(deviceIndexLog.getValue()));
            DeviceAlarm deviceAlarm = deviceAlarmKeyAndDeviceAlarmMap.get(deviceIndexLog.getDeviceId() + "_" + deviceIndexLog.getIndexId());
            // 如果是报警是否处理
            if (Objects.nonNull(deviceAlarm) && deviceAlarm.isAlarm(deviceIndexLog.getValue())) {
                deviceIndexLogVo.setAlarmFlag(1);
                deviceIndexLogVo.setAlarmLevel(deviceAlarm.getAlarmLevel(deviceIndexLog.getValue()));
                deviceIndexLogVo.setHandleFlag(deviceAlarmLogService.isHandled(deviceIndexLog.getIndexId()) ? 1 : 0);
            } else {
                deviceIndexLogVo.setAlarmFlag(0);
            }
            return deviceIndexLogVo;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public DeviceAlarmLogVo getAlarmHandleData(String deviceId, String indexId) {
        return deviceAlarmLogService.getLast(deviceId,indexId);
    }

    @Override
    public AnalyseManualInspection monthManualInspectionAnalyse() {
        Date stop = new Date();
        Date start = DateUtils.monthsBefore(1, stop);
        return manualInspectionOutcomeService.analyse(start, stop);
    }

    @Override
    public AnalyseManualInspection allManualInspectionAnalyse() {
        return manualInspectionOutcomeService.analyse(null, null);
    }

    @Override
    public List<AnalyseInspection> inspectionAnalyse() {
        // 智能巡检
        AnalyseInspection analyse = intelligentInspectionOutcomeService.analyse();
        analyse.setType("智能巡检");
        List<AnalyseInspection> analyseInspections = manualInspectionOutcomeService.analyseByType();
        // 日常巡检
        analyseInspections.add(analyse);
        // 年度巡检
        return analyseInspections;
    }

    @Override
    public List<IntelligentInspectionOutcomeAppVo> findHalfMonthInspection() {
        return intelligentInspectionOutcomeService.findHalfMonth();
    }

    @Override
    public List<ManualInspectionOutcomeAppVo> findHalfMonthManualInspection() {
        return manualInspectionOutcomeService.findHalfMonth();
    }

    @Override
    public IntelligentInspectionOutcomeAppVo getInspectionData(String planCode) {
        return intelligentInspectionOutcomeService.getInspectionData(planCode);
    }

    @Override
    public ManualInspectionOutcomeAppVo getManualInspectionData(String planCode) {
        return manualInspectionOutcomeService.getManualInspectionData(planCode);
    }

    @Override
    public void manualInspectionDisposeWrite(ManualInspectionOutcomeAdd manualInspectionOutcomeAdd) {
        manualInspectionOutcomeService.disposeWrite(manualInspectionOutcomeAdd);
    }
}
