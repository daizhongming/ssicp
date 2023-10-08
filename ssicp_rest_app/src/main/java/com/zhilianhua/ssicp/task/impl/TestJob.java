package com.zhilianhua.ssicp.task.impl;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("TestJob");
        String name = context.getJobDetail().getKey().getName();
        String group = context.getJobDetail().getKey().getGroup();
        String description = context.getJobDetail().getDescription();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        log.debug("name:{},group:{},description:{},jobDataMap:{}", name, group, description, JSON.toJSONString(jobDataMap));
    }
}
