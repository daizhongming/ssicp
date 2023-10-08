package com.zhilianhua.ssicp.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class IntelligentInspectionPlanAdd {
    /**
     * 计划编号
     */
    private String planCode;
    
    /**
     * 计划名称
     */
    @NotBlank(message = "计划名称不能为空")
    private String planName;

    /**
     * 负责人名称
     */
    @NotBlank(message = "负责人不能为空")
    private String responsibleName;

    /**
     * 巡检周期
     */
    private String cycle;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 巡检时间集合
     */
    @NotBlank(message = "时间段不能为空")
    private String inspectionTimes;

    /**
     * 巡检单位
     */
    private String cycleUnit;

    /**
     * 管段id集合
     */
//    @NotBlank(message = "管段不能为空")
    private List<String> pipeIds;

    /**
     * 管线id集合
     */
//    @NotBlank(message = "管线不能为空")
    private String pipelineIds;

    /**
     * 巡检项
     */
    private String item;

    /**
     * 指标模板集合
     */
    private Map<String, List<String>> metricTemplateDTOS;
}
