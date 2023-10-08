package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.IntelligentInspectionPlan;
import com.zhilianhua.ssicp.entity.request.searchparams.IntelligentInspectionSearchParams;
import com.zhilianhua.ssicp.entity.response.IntelligentInspectionPlanVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author 代忠明
 * @description 智能巡检计划
 * @date 2023-07-05
 */
public interface
IntelligentInspectionPlanMapper {

    void insert(IntelligentInspectionPlan intelligentInspectionPlan);

    IntelligentInspectionPlan findByid(@Param("planCode") String planCode);

    void update(IntelligentInspectionPlan intelligentInspectionPlan);

    void delete(@Param("planCode") String planCode);

    PageResultEntity<IntelligentInspectionPlanVO> pageList(IntelligentInspectionSearchParams intelligentInspectionSearchParams);
}