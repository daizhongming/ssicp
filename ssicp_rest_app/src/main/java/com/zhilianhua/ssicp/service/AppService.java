package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.app.TodosVO;
import com.zhilianhua.ssicp.entity.db.FileInfo;
import com.zhilianhua.ssicp.entity.db.Message;
import com.zhilianhua.ssicp.entity.db.SteamIndexLog;
import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.request.app.ReadMessageParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.DeviceIndexLogVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AppService {
    List<TodosVO> todos(String userName);

    void readMessage(ReadMessageParams readMessageParams);

    List<SteamIndexLog> productionAndConsumption();

    User findByName(String userName);

    List<FileInfo> uploadMulti(List<MultipartFile> collect);

    List<Message> messages(String userName);

    List<MessageAnalyseEntity> messagesAnalyse(String userName);

    List<AnalyseEntity> indexAlarmAnalyse();

    List<AnalyseEntity> deviceAlarmAnalyse();

    List<AnalyseEntity> deviceCountAnalyse();

    List<DeviceIndexLogVo> deviceIndexLogs();

    DeviceAlarmLogVo getAlarmHandleData(String deviceId, String indexId);

    AnalyseManualInspection monthManualInspectionAnalyse();

    AnalyseManualInspection allManualInspectionAnalyse();

    List<AnalyseInspection> inspectionAnalyse();

    List<IntelligentInspectionOutcomeAppVo> findHalfMonthInspection();

    List<ManualInspectionOutcomeAppVo> findHalfMonthManualInspection();

    IntelligentInspectionOutcomeAppVo getInspectionData(String planCode);

    ManualInspectionOutcomeAppVo getManualInspectionData(String planCode);

    void manualInspectionDisposeWrite(ManualInspectionOutcomeAdd manualInspectionOutcomeAdd);

    List<TodoAnalyseEntity> todoAnalyse(String userName);
}
