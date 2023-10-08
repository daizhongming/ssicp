package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.ManualInspectionOutcomeParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ManualInspectionOutcomeMapper {

    PageResultEntity<ManualInspectionOutcomeVO> search(ManualInspectionOutcomeParams manualInspectionOutcomeParams);

    List<ManualOutcomeDetails> detailsByoutcomeCode(@Param("outcomeCode") String outcomeCode);

    List<ManualOutcomeImage> imageByoutcomeCode(@Param("outcomeCode") String outcomeCode);

    List<ManualOutcomeObserve> observeByoutcomeCode(@Param("outcomeCode") String outcomeCode);

    void updateDispose(ManualOutcomeDetails manualOutcomeDetails);


    void updateObserve(ManualOutcomeObserve manualOutcomeObserve);


    void updateImage(ManualOutcomeImage manualOutcomeImage);


    void update(ManualInspectionOutcome manualInspectionOutcome);

    AnalyseManualInspection analyse(@Param("from") Date from, @Param("to") Date to);

    List<AnalyseInspection> analyseByType();
    TodoAnalyseEntity todoAnalyseForInspection(@Param("responsibleName") String responsibleName);
    TodoAnalyseEntity todoAnalyseForError(@Param("responsibleName") String responsibleName);

    void add(ManualInspectionOutcome manualInspectionOutcome);

    void addImages(List<ManualOutcomeImage> manualOutcomeImages);

    void addObserve(List<ManualOutcomeObserve> manualOutcomeObserves);

    void addDetails(List<ManualOutcomeDetails> manualOutcomeDetails);


    List<ManualInspectionOutcomeAppVo> findHalfMonth();

    List<ManualInspectionOutcomeAppVo> findAllByUser(@Param("responsibleName") String responsibleName);

    ManualInspectionOutcomeAppVo getManualInspectionData(@Param("planCode") String planCode);

    List<InspectionExceptionsVO> queryExceptionToDos();

    List<DeviceIndexLog> findIndexLogsHistory(@Param("deviceId") String deviceId, @Param("index") String index);

    List<AlarmactionInfo> getdispose(@Param("outcomeCode") String outcomeCode);

    PageResultEntity<ManualInspectionOutcomeVO> searchDeputy(ManualInspectionOutcomeParams manualInspectionOutcomeParams);
}