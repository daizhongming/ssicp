package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.IntelligentInspectionOutcome;
import com.zhilianhua.ssicp.entity.request.OutcomeDispose;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 代忠明
 * @description 智能巡检结果
 * @date 2023-07-05
 */
public interface IntelligentInspectionOutcomeService {

    PageResultEntity<IntelligentInspectionOutcomeVO> findPage(IntelligentInspectionSearchParams intelligentInspectionSearchParams);

    void insert(IntelligentInspectionOutcome intelligentInspectionOutcome);

    Map<String, Map<String, List<IntelligentOutcomeDetailsVo>>> load(String outcomeCode);

    void dispose(List<OutcomeDispose> outcomeDisposes, String outcomeCode);

    AnalyseInspection analyse();
    TodoAnalyseEntity todoAnalyse(String userName);

    List<IntelligentInspectionOutcomeAppVo> findHalfMonth();

    List<IntelligentInspectionOutcomeAppVo> findAllErrorByUser(String responsibleName);

    IntelligentInspectionOutcomeAppVo getInspectionData(String planCode);

    List<InspectionExceptionsVO> queryExceptionToDos();

    List<OutcomeDispose> getdispose(String outcomeCode);
}