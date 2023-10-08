package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;

@Data
public class DecisionPlanSearchParams extends PageParams {
    private String name;
    private String fileType;
}
