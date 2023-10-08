package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.BenefitAnalysisReport;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportAdd;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.BenefitAnalysisReportSearchParams;
import com.zhilianhua.ssicp.entity.response.BenefitAnalysisReportVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

public interface BenefitAnalysisReportMapper {

    PageResultEntity<BenefitAnalysisReportVo> search(BenefitAnalysisReportSearchParams benefitAnalysisReportSearchParams);

    void add(BenefitAnalysisReportAdd benefitAnalysisReportAdd);

    void delete(@Param("id") Integer id);
    BenefitAnalysisReport findById(@Param("id") Integer id);

    void update(BenefitAnalysisReportUpdate benefitAnalysisReportUpdate);

    void updateReadCount(Integer id);
}
