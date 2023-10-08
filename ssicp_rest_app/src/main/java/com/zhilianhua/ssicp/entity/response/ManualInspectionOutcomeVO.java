package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.ManualInspectionOutcome;
import lombok.Data;

@Data
public class ManualInspectionOutcomeVO extends ManualInspectionOutcome {

    /**
     * 负责人昵称
     */
    private String nickName;
}
