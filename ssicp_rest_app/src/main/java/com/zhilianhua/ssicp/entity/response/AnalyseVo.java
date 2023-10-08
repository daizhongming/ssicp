package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class AnalyseVo {
    private String groupByName;
    private Integer handledTimes = 0;
    private Integer unHandleTimes = 0;
}
