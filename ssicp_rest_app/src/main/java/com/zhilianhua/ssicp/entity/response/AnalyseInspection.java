package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class AnalyseInspection {
    private String type;
    private Integer total=0;
    private Integer errorCount=0;
}
