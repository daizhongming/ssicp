package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;

@Data
public class ConfigParams extends PageParams {
    private String keyword;
    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
