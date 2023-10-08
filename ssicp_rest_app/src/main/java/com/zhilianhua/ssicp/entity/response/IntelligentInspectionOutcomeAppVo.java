package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class IntelligentInspectionOutcomeAppVo extends IntelligentInspectionOutcomeVO {
    private String responsibleNickName;
    private List<IntelligentOutcomeDetailsVo> intelligentOutcomeDetailsVos;
}
