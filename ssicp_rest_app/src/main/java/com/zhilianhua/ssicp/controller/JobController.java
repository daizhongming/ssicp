package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.request.JobAdd;
import com.zhilianhua.ssicp.entity.request.JobOnceParams;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.JobService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/jobs")
@Privilege("任务管理")
@RestController
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("")
    @Privilege("查询全部")
    public ResponseEntity findAll() {
        return ResponseEntityUtils.success(jobService.findAll());
    }

    @PostMapping("")
    @Privilege("新增任务")
    public ResponseEntity add(@RequestBody JobAdd jobAdd) {
        jobService.add(jobAdd);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{jobName}/{jobGroupName}")
    @Privilege("修改任务")
    public ResponseEntity update(@RequestBody JobAdd JobAdd) {
        jobService.update(JobAdd);
        return ResponseEntityUtils.success();
    }

    @DeleteMapping("/{jobName}/{jobGroupName}")
    @Privilege("删除任务")
    public ResponseEntity delete(@PathVariable String jobName, @PathVariable String jobGroupName) {
        jobService.delete(jobName, jobGroupName);
        return ResponseEntityUtils.success();
    }

    @PutMapping("/{jobName}/{jobGroupName}/status")
    @Privilege("启动暂停任务")
    public ResponseEntity updateStatus(@PathVariable String jobName, @PathVariable String jobGroupName, Integer status) {
        jobService.updateStatus(jobName, jobGroupName, status);
        return ResponseEntityUtils.success();
    }


    @PostMapping("/{jobName}/{jobGroupName}")
    @Privilege("手动触发定时任务")
    public ResponseEntity runOne(@RequestBody @Valid JobOnceParams jobOnceParams) {
        jobService.runOne(jobOnceParams);
        return ResponseEntityUtils.success();
    }
}
