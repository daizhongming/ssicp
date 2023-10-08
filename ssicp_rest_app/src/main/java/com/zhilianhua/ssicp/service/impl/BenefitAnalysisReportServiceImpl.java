package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.BenefitAnalysisReport;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportAdd;
import com.zhilianhua.ssicp.entity.request.BenefitAnalysisReportUpdate;
import com.zhilianhua.ssicp.entity.request.searchparams.BenefitAnalysisReportSearchParams;
import com.zhilianhua.ssicp.entity.response.BenefitAnalysisReportVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.BenefitAnalysisReportMapper;
import com.zhilianhua.ssicp.service.BenefitAnalysisReportService;
import com.zhilianhua.ssicp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BenefitAnalysisReportServiceImpl implements BenefitAnalysisReportService {
    @Autowired
    private BenefitAnalysisReportMapper benefitAnalysisReportMapper;
    @Autowired
    private FileService fileService;

    @Override
    public PageResultEntity<BenefitAnalysisReportVo> findPage(BenefitAnalysisReportSearchParams benefitAnalysisReportSearchParams) {
        return benefitAnalysisReportMapper.search(benefitAnalysisReportSearchParams);
    }

    @Override
    public void add(BenefitAnalysisReportAdd benefitAnalysisReportAdd) {
        benefitAnalysisReportMapper.add(benefitAnalysisReportAdd);
    }

    @Override
    public void update(BenefitAnalysisReportUpdate benefitAnalysisReportUpdate) {
        benefitAnalysisReportMapper.update(benefitAnalysisReportUpdate);
    }

    @Override
    public void delete(Integer id) {
        BenefitAnalysisReport byId = benefitAnalysisReportMapper.findById(id);
        if (Objects.nonNull(byId)) {
            benefitAnalysisReportMapper.delete(id);
            fileService.deleteByCode(byId.getFileCode());
        }
    }

    @Override
    public void updateReadCount(Integer id) {
        benefitAnalysisReportMapper.updateReadCount(id);
    }
}
