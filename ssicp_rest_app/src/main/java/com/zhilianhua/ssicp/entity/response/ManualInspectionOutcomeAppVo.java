package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.ManualInspectionOutcome;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeDetails;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeImage;
import com.zhilianhua.ssicp.entity.db.ManualOutcomeObserve;
import lombok.Data;

import java.util.List;

@Data
public class ManualInspectionOutcomeAppVo extends ManualInspectionOutcome {
    private String responsibleNickName;
    private List<ManualOutcomeDetails> manualOutcomeDetails;
    private List<ManualOutcomeImage> manualOutcomeImages;
    private List<ManualOutcomeObserve> manualOutcomeObserves;
    private String inspectionType;
    private String inspectionTypeName;
}
