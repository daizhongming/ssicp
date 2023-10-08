package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.DecisionPlan;
import lombok.Data;

@Data
public class DecisionPlanVo extends DecisionPlan {
    private String fileName;
    private Long fileSize;
    private String fileType;
}
