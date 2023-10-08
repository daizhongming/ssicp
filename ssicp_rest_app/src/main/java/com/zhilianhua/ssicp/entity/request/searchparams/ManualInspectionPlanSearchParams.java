package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Data
public class ManualInspectionPlanSearchParams extends OrderPageParams {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 负责人名称
     */
    private String responsibleName;

    /**
     * 巡检类型
     */
    private String inspectionType;

    /**
     * 巡检类型
     */
    private String inspectionStart;

    @Override
    public Map<String, String> setPropsMap() {
        return OrderPageParams.mapBuilder("startTime", "start_time");
    }
}
