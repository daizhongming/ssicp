package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportAdd;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.BenefitAnalysisReportSearchParams;
import com.zhilianhua.ssicp.entity.response.BenefitAnalysisReportVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

public interface BenefitAnalysisReportService {
    PageResultEntity<BenefitAnalysisReportVo> findPage(BenefitAnalysisReportSearchParams benefitAnalysisReportSearchParams);

    void add(BenefitAnalysisReportAdd benefitAnalysisReportAdd);

    void update(BenefitAnalysisReportUpdate benefitAnalysisReportUpdate);

    void delete(Integer id);

    void updateReadCount(Integer id);
}
