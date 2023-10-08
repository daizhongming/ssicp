package com.zhilianhua.ssicp.task.impl;

import com.zhilianhua.ssicp.remote.AiModelService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;

@Slf4j
@Component
public class AiModelCalcJob implements Job {
    @Autowired
    private AiModelService aiModelService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("AiModelCalcJob start");
        aiModelService.calc();
        log.debug("AiModelCalcJob end");
    }
}
