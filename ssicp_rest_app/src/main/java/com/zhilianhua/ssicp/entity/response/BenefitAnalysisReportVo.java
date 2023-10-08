package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.BenefitAnalysisReport;
import lombok.Data;

@Data
public class BenefitAnalysisReportVo extends BenefitAnalysisReport {
    private String fileName;
    private Long fileSize;
    private String fileType;
}
