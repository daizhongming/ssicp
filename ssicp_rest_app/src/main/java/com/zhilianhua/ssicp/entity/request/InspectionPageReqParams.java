package com.zhilianhua.ssicp.entity.request;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;

@Data
public class InspectionPageReqParams extends PageParams {
    /**
     * 开始起始时间
     */
    private String startTime;

    /**
     * 结束起始时间
     */
    private String endTime;

    /**
     * 处理类型
     */
    private Integer disposeState;

    /**
     * 异常类型
     */
    private String abnormalTypes;

    /**
     * 巡检类型
     */
    private Integer inspectionType;

}
