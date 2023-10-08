package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BenefitAnalysisReportUpdate {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotBlank(message = "预案名称不能为空")
    private String name;
    @NotBlank(message = "文件不能为空")
    private String fileCode;
}
