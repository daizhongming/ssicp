package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.IntelligentInspectionOutcome;
import lombok.Data;

@Data
public class IntelligentInspectionOutcomeVO extends IntelligentInspectionOutcome {

    private String planName;
    private String nickName;
    private Integer instrumentCount;
    private Integer steamTrapsCount;
    private Integer valveCount;
    private Integer pipeCount;
    private Integer steamProductionPointCount;
    private Integer withSteamSpotsCount;
}
