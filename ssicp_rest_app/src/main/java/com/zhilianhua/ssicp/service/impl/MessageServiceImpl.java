package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.app.MessageAnalyseTypeEnum;
import com.zhilianhua.ssicp.entity.app.MessageTypeEnum;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.response.MessageAnalyseEntity;
import com.zhilianhua.ssicp.mapper.MessageMapper;
import com.zhilianhua.ssicp.service.DeviceAlarmService;
import com.zhilianhua.ssicp.service.MessageService;
import com.zhilianhua.ssicp.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceAlarmService deviceAlarmService;

    @Override
    public void add(Message message) {
        Message load = messageMapper.load(message.getType(), message.getBusinessId(), message.getUserName());
        if (Objects.isNull(load)) {
            messageMapper.add(message);
        } else {
            load.setTitle(message.getTitle());
            load.setAnalyseType(message.getAnalyseType());
            messageMapper.update(load);
        }
    }

    @Override
    public void addAlarm(DeviceAlarmLog deviceAlarmLog) {
        List<User> allInspector = userService.findAllInspector();
        Message message = new Message();
        message.setType(MessageTypeEnum.ALARM.getType());
        message.setAnalyseType(MessageAnalyseTypeEnum.ALARM.getType());
        message.setBusinessId(deviceAlarmLog.getDeviceId() + "_" + deviceAlarmLog.getIndexId());
        message.setTitle(deviceAlarmLog.getDeviceName() + deviceAlarmLog.getIndexName() + deviceAlarmLog.getAlarmLevelName());
        if (CollectionUtils.isNotEmpty(allInspector)) {
            allInspector.forEach(user -> {
                message.setUserName(user.getName());
                add(message);
            });
        }
    }

    @Override
    public void addIntelligentInspectionError(IntelligentInspectionPlan intelligentInspectionPlan) {
        Message message = new Message();
        message.setBusinessId(intelligentInspectionPlan.getPlanCode());
        message.setType(MessageTypeEnum.MACHINE_INSPECTION.getType());
        message.setAnalyseType(MessageAnalyseTypeEnum.ERROR.getType());
        message.setUserName(intelligentInspectionPlan.getResponsibleName());
        message.setTitle(intelligentInspectionPlan.getPlanName() + "异常");
        add(message);
    }

    @Override
    public void addManualInspectionError(ManualInspectionOutcomeAdd manualInspectionOutcomeAdd) {
        Message message = new Message();
        message.setType(MessageTypeEnum.MANUAL_INSPECTION.getType());
        message.setAnalyseType(MessageAnalyseTypeEnum.ERROR.getType());
        message.setUserName(manualInspectionOutcomeAdd.getResponsibleName());
        message.setBusinessId(manualInspectionOutcomeAdd.getPlanCode());
        message.setTitle(manualInspectionOutcomeAdd.getPlanName() + "异常");
        add(message);
    }

    @Override
    public void addManualInspection(ManualInspectionOutcome manualInspectionOutcome) {
        Message message = new Message();
        message.setBusinessId(manualInspectionOutcome.getPlanCode());
        message.setType(MessageTypeEnum.MANUAL_INSPECTION.getType());
        message.setAnalyseType(MessageAnalyseTypeEnum.INSPECTION.getType());
        message.setTitle(manualInspectionOutcome.getPlanName() + "人工巡检");
        message.setUserName(manualInspectionOutcome.getResponsibleName());
        add(message);
    }

    @Override
    public void removeAlarm(String deviceId, String indexId) {
        messageMapper.readByTypeAndBusinessId(MessageTypeEnum.ALARM.getType(), deviceId + "_" + indexId);
    }

    @Override
    public void removeMessge(String type, String indexId) {
        messageMapper.readByTypeAndBusinessId(type, indexId);
    }

    @Override
    public void readAll(String userName) {
        messageMapper.readAll(userName);
    }

    @Override
    public void readById(Long id) {
        messageMapper.readById(id);
    }

    @Override
    public List<Message> findByUser(String userName) {
        return messageMapper.findByUser(userName);
    }

    @Override
    public List<MessageAnalyseEntity> analyse(String userName) {
        return messageMapper.analyse(userName);
    }
}
