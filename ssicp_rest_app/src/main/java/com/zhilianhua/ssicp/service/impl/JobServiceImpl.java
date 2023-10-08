package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.JobOnceParams;
import com.zhilianhua.ssicp.entity.response.JobVo;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.service.JobService;
import com.zhilianhua.ssicp.task.check.JobParamsCheckerAnnotation;
import com.zhilianhua.ssicp.task.check.JobParamsUtils;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    private static final String EXPRESSION_SPLITTER = ";";

    @Autowired
    private Scheduler scheduler;

    @Override
    public List<JobVo> findAll() {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyJobGroup()).stream().map(this::get).collect(Collectors.toList());
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(JobAdd jobAdd) {
        try {
            JobKey jobKey = getJobKey(jobAdd.getJobName(), jobAdd.getJobGroupName());
            if (scheduler.checkExists(jobKey)) {
                throw BusinessException.instance("任务已经存在");
            }
            Class<? extends Job> jobClass = getJobClass(jobAdd.getJobClass());
            JobDataMap jobDataMap = new JobDataMap(JSON.parseObject(jobAdd.getJobDataMap(), HashMap.class));
            JobParamsCheckerAnnotation jobParamsCheckerAnnotation = jobClass.getAnnotation(JobParamsCheckerAnnotation.class);
            if (Objects.nonNull(jobParamsCheckerAnnotation)) {
                Class<?> value = jobParamsCheckerAnnotation.value();
                JobParamsUtils.check(jobDataMap, value);
            }
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).setJobData(jobDataMap).withDescription(jobAdd.getDescription()).build();
            if (jobAdd.getStartTime().before(new Date())) {
                // 如果计划开始时间比现在大，才去设置的计划开始时间，否则去当前时间
                jobAdd.setStartTime(new Date());
            }
            List<ScheduleBuilder> scheduleBuilderList = new ArrayList();
            if (StringUtils.isNoneBlank(jobAdd.getCronExpressions())) {
                // cron表达式方式调度
                scheduleBuilderList = Arrays.stream(jobAdd.getCronExpressions().split(EXPRESSION_SPLITTER)).map(cronExpression -> CronScheduleBuilder.cronSchedule(cronExpression)).collect(Collectors.toList());
            } else if (StringUtils.isNoneBlank(jobAdd.getTimeIntervalString())) {
                // 时间间隔方式调度
                String[] split = jobAdd.getTimeIntervalString().split(EXPRESSION_SPLITTER);
                int timeInterval = Integer.parseInt(split[0]);
                DateBuilder.IntervalUnit unit = DateBuilder.IntervalUnit.valueOf(split[1]);
                CalendarIntervalScheduleBuilder calendarIntervalScheduleBuilder = CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withInterval(timeInterval, unit);
                scheduleBuilderList.add(calendarIntervalScheduleBuilder);
            } else {
                throw BusinessException.instance("调度参数为空");
            }
            Set<Trigger> cronTriggerSet = scheduleBuilderList.stream().map(scheduleBuilder -> TriggerBuilder.newTrigger().withIdentity(IdUtils.uuid(), jobAdd.getJobGroupName()).startAt(jobAdd.getStartTime()).withSchedule(scheduleBuilder).build()).collect(Collectors.toSet());
            scheduler.scheduleJob(jobDetail, cronTriggerSet, true);
            if (Objects.nonNull(jobAdd.getStatus()) && jobAdd.getStatus().intValue() == 0) {
                scheduler.pauseJob(jobKey);
            }
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    private Class getJobClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw BusinessException.instance(className + "不存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(JobAdd jobAdd) {
        try {
            JobKey jobKey = getJobKey(jobAdd.getJobName(), jobAdd.getJobGroupName());
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
            add(jobAdd);
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = getJobKey(jobName, jobGroupName);
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(String jobName, String jobGroupName, Integer status) {
        try {
            JobKey jobKey = getJobKey(jobName, jobGroupName);
            validJobExists(jobKey);
            if (status.intValue() == 0) {
                scheduler.pauseJob(jobKey);
            } else if (status.intValue() == 1) {
                scheduler.resumeJob(jobKey);
            }
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    @Override
    public JobVo get(String jobName, String jobGroupName) {
        return get(getJobKey(jobName, jobGroupName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void runOne(JobOnceParams jobOnceParams) {
        try {
            JobKey jobKey = getJobKey(jobOnceParams.getJobName(), jobOnceParams.getJobGroupName());
            validJobExists(jobKey);
            scheduler.triggerJob(jobKey, new JobDataMap(JSON.parseObject(jobOnceParams.getJobDataMap(), HashMap.class)));
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    public JobVo get(JobKey jobKey) {
        try {
            JobVo jobVo = new JobVo();
            jobVo.setJobName(jobKey.getName());
            jobVo.setJobGroupName(jobKey.getGroup());
            JobDetail jobDetail = null;
            try {
                jobDetail = scheduler.getJobDetail(jobKey);
            } catch (SchedulerException e) {
                return jobVo;
            }
            jobVo.setJobClass(jobDetail.getJobClass().getName());
            jobVo.setJobDataMap(JSON.toJSONString(jobDetail.getJobDataMap()));
            jobVo.setDescription(jobDetail.getDescription());
            List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
            List<JobVo.TriggerVo> triggerVoList = jobVo.getTriggerVoList();
            for (Trigger trigger : triggersOfJob) {
                JobVo.TriggerVo triggerVo = new JobVo.TriggerVo();
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                triggerVo.setStatus(triggerState.name());
                triggerVo.setStartTime(trigger.getStartTime());
                triggerVo.setNextFireTime(trigger.getNextFireTime());
                if ((trigger instanceof CronTrigger)) {
                    triggerVo.setCronExpression(((CronTrigger) (trigger)).getCronExpression());
                } else if ((trigger instanceof CalendarIntervalTrigger)) {
                    CalendarIntervalTrigger calendarIntervalTrigger = (CalendarIntervalTrigger) trigger;
                    triggerVo.setTimeIntervalString(calendarIntervalTrigger.getRepeatInterval() + EXPRESSION_SPLITTER + calendarIntervalTrigger.getRepeatIntervalUnit().name());
                } else {
                    continue;
                }
                triggerVoList.add(triggerVo);
            }
            if (CollectionUtils.isEmpty(triggersOfJob)) {
                throw BusinessException.instance("任务无触发");
            }
            jobVo.setStartTime(triggerVoList.get(0).getStartTime());
            jobVo.setNextFireTime(triggerVoList.stream().map(JobVo.TriggerVo::getNextFireTime).sorted().findFirst().get());
            if (StringUtils.isNoneBlank(triggerVoList.get(0).getTimeIntervalString())) {
                jobVo.setTimeIntervalString(triggerVoList.get(0).getTimeIntervalString());
            } else {
                jobVo.setCronExpressions(triggerVoList.stream().map(JobVo.TriggerVo::getCronExpression).collect(Collectors.joining(EXPRESSION_SPLITTER)));
            }
            jobVo.setStatus(getStatus(triggerVoList));
            return jobVo;
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }

    private String getStatus(List<JobVo.TriggerVo> triggerVoList) {
        if (triggerVoList.stream().filter(triggerVo -> triggerVo.getStatus().equals(Trigger.TriggerState.ERROR.name())).findAny().isPresent()) {
            return Trigger.TriggerState.ERROR.name();
        }
        JobVo.TriggerVo triggerVo = triggerVoList.get(0);
        if (triggerVo.getStatus().equals(Trigger.TriggerState.PAUSED.name())) {
            return Trigger.TriggerState.PAUSED.name();
        }
        return Trigger.TriggerState.NORMAL.name();
    }

    public JobKey getJobKey(String jobName, String jobGroupName) {
        return JobKey.jobKey(jobName, jobGroupName);
    }

    public void validJobExists(JobKey jobKey) {
        try {
            if (!scheduler.checkExists(jobKey)) {
                throw BusinessException.instance("任务不存在");
            }
        } catch (SchedulerException e) {
            throw BusinessException.instance(e.getMessage());
        }
    }
}
