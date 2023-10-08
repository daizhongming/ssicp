package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.AlarmactionInfo;
import com.zhilianhua.ssicp.entity.db.IntelligentInspectionOutcome;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 代忠明
 * @description 智能巡检结果
 * @date 2023-07-05
 */
public interface IntelligentInspectionOutcomeMapper {

    PageResultEntity<IntelligentInspectionOutcomeVO> search(IntelligentInspectionSearchParams intelligentInspectionSearchParams);

    void insert(IntelligentInspectionOutcome intelligentInspectionOutcome);

    IntelligentInspectionOutcome load(@Param("outcomeCode") String outcomeCode);

    void update(IntelligentInspectionOutcome intelligentInspectionOutcome);

    AnalyseInspection analyse();
    TodoAnalyseEntity todoAnalyse(@Param("responsibleName") String responsibleName);

    List<IntelligentInspectionOutcomeAppVo> findHalfMonth();

    List<IntelligentInspectionOutcomeAppVo> findAllErrorByUser(@Param("responsibleName") String responsibleName);

    IntelligentInspectionOutcomeAppVo getInspectionData(@Param("planCode") String planCode);

    List<InspectionExceptionsVO> queryExceptionToDos();

    List<AlarmactionInfo> getdisposeByoutcomeCode(@Param("outcomeCode") String outcomeCode);
}