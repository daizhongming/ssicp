package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class MetricTemplateDTO {
    private String deviceType;

    private List<String> values;
}
