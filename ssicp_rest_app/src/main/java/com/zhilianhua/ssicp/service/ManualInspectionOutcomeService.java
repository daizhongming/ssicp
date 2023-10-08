package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.ManualInspectionOutcome;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeDetails;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeImage;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeObserve;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.request.OutcomeDispose;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionOutcomeParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ManualInspectionOutcomeService {

    PageResultEntity<ManualInspectionOutcomeVO> findPage(ManualInspectionOutcomeParams manualInspectionOutcomeParams);

    Map<String, Map<String, ManualOutcomeDetailsVO>> load(String outcomeCode);

    void dispose(List<OutcomeDispose> outcomeDisposes, String outcomeCode);

    AnalyseManualInspection analyse(Date from, Date to);

    List<AnalyseInspection> analyseByType();

    TodoAnalyseEntity todoAnalyseForInspection(String userName);

    TodoAnalyseEntity todoAnalyseForError(String userName);

    void add(ManualInspectionOutcome manualInspectionOutcome);

    void addImages(List<ManualOutcomeImage> manualOutcomeImages);

    void addObserve(List<ManualOutcomeObserve> manualOutcomeObserves);

    void addDetails(List<ManualOutcomeDetails> manualOutcomeDetails);

    List<ManualInspectionOutcomeAppVo> findHalfMonth();

    List<ManualInspectionOutcomeAppVo> findAllByUser(String responsibleName);

    ManualInspectionOutcomeAppVo getManualInspectionData(@Param("planCode") String planCode);

    List<InspectionExceptionsVO> queryExceptionToDos();

    List<ManualOutcomeObserve> observeByoutcomeCode(String outcomeCode);

    List<ManualOutcomeDetails> detailsByoutcomeCode(String outcomeCode);

    void disposeWrite(ManualInspectionOutcomeAdd manualInspectionOutcomeAdd);

    List<DeviceIndexLog> findIndexLogsHistory(String deviceId, String index);

    List<OutcomeDispose> getdispose(String outcomeCode);
}