package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.JobOnceParams;
import com.zhilianhua.ssicp.entity.response.JobVo;

import java.util.List;

public interface JobService {
    List<JobVo> findAll();

    void add(JobAdd jobAdd);

    void update(JobAdd jobAdd);

    void delete(String jobName, String jobGroupName);

    void updateStatus(String jobName, String jobGroupName, Integer status);

    JobVo get(String jobName, String jobGroupName);

    void runOne(JobOnceParams jobOnceParams);

}
