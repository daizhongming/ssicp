package com.zhilianhua.ssicp;

import com.zhilianhua.ssicp.task.check.JobParamsUtils;
import com.zhilianhua.ssicp.task.entity.LogGenerationJobParams;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.JobDataMap;

@Slf4j
public class JobParamsUtilsTest {
    @Test
    public void test() {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("low", 0.1);
        jobDataMap.put("senior", 0.2);
        jobDataMap.put("seniorSenior", 0.3);
        LogGenerationJobParams logGenerationJobParams = JobParamsUtils.parse(jobDataMap, LogGenerationJobParams.class);
        log.debug("logGenerationJobParams:{}", logGenerationJobParams);
    }
}
