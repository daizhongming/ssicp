package com.zhilianhua.ssicp.service;


import com.zhilianhua.ssicp.entity.db.DeviceAlarmLog;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import com.zhilianhua.ssicp.entity.db.ManualInspectionOutcome;
import com.zhilianhua.ssicp.entity.db.Message;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.response.MessageAnalyseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageService {
    void add(Message message);

    void addAlarm(DeviceAlarmLog deviceAlarmLog);

    void addIntelligentInspectionError(IntelligentInspectionPlan intelligentInspectionPlan);
    void addManualInspectionError(ManualInspectionOutcomeAdd manualInspectionOutcomeAdd);
    void addManualInspection(ManualInspectionOutcome manualInspectionOutcome);
    void removeAlarm(String deviceId,String indexId);

    void removeMessge(String type, String indexId);

    void readAll(String userName);
    void readById(Long id);

    List<Message> findByUser(String userName);
    List<MessageAnalyseEntity> analyse(String userName);
}