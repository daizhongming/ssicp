package com.zhilianhua.ssicp;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
public class JobTest {
    @Autowired
    private Scheduler scheduler;

    @Test
    public void test() throws SchedulerException {
        List<String> jobGroupNames = scheduler.getJobGroupNames();
        log.info("jobGroupNames:{}", JSON.toJSONString(jobGroupNames));
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        for (JobKey jobKey : jobKeys) {
            log.info("----------------------------------------------------------------------");
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            log.info("job:{}", JSON.toJSONString(jobDetail));
            List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggersOfJob) {
                log.info("trigger:{}", JSON.toJSONString(trigger));
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                log.info("triggerState:{}", JSON.toJSONString(triggerState));
            }
        }
    }
}
