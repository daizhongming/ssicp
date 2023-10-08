package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class JobVo {
    private String jobName;
    private String jobGroupName;
    private String jobClass;
    private String jobDataMap;
    private String description;
    private Date nextFireTime;
    private Date startTime;
    private String status;
    private String cronExpressions;
    private String timeIntervalString;
    private List<TriggerVo> triggerVoList = new ArrayList();

    @Data
    public static
    class TriggerVo {
        private Date nextFireTime;
        private Date startTime;
        private String status;
        private String cronExpression;
        private String timeIntervalString;
    }
}
