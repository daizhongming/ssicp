package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JobOnceParams {
    @NotBlank
    private String jobName;
    @NotBlank
    private String jobGroupName;
    private String jobDataMap = "{}";
}
