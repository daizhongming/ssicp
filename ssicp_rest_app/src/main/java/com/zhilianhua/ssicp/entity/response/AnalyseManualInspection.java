package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class AnalyseManualInspection {
    private Integer total=0;
    private Integer UndoCount=0;
    private Integer expireCount=0;
    private Integer completeCount=0;
    private Integer errorUnHandleCount=0;
    private Integer errorHandledCount=0;
}
