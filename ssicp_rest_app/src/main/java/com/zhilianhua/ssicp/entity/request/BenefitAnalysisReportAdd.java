package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BenefitAnalysisReportAdd {
    @NotBlank(message = "预案名称不能为空")
    private String name;
    @NotBlank(message = "文件不能为空")
    private String fileCode;
    private String uploadUser;
}
