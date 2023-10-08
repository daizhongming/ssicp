package com.zhilianhua.ssicp.entity.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class JobAdd {
    @NotBlank
    private String jobName;
    @NotBlank
    private String jobGroupName;
    private Integer status = 1;
    @NotBlank
    private String jobClass;
    /**
     * cron表达式集合：0 0 0 * * ?;0 0 0 * * ?;0 0 0 * * ?
     */
    private String cronExpressions;
    /**
     * 时间间隔：1;YEAR
     * 时间单位可以选择 MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR
     */
    private String timeIntervalString;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime = new Date();
    private String description;
    private String jobDataMap = "{}";
}
