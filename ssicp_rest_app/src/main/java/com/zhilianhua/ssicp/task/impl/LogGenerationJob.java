package com.zhilianhua.ssicp.task.impl;

import com.zhilianhua.ssicp.async.DeviceIndexLogAsyncHandler;
import com.zhilianhua.ssicp.entity.db.Device;
import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.service.DeviceAlarmService;
import com.zhilianhua.ssicp.service.DeviceService;
import com.zhilianhua.ssicp.service.DictDataService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
//@JobParamsCheckerAnnotation(LogGenerationJobParams.class)
public class LogGenerationJob implements Job {
    @Autowired
    private DeviceAlarmService deviceAlarmService;
    @Autowired
    private DeviceIndexLogAsyncHandler deviceIndexLogAsyncHandler;

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DictDataService dictDataService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.debug("LogGenerationTask start");
        // 拿到所有设备，循环设备以及字典配置的指标项来生成数据
        List<Device> deviceList = deviceService.findAll();
        Map<String, List<DictData>> stringListMap = dictDataService.listAll(DeviceTypeEnum.getIndexTypeCodes());
        for (Device device : deviceList) {
            DeviceTypeEnum.getByParentDeviceType(device.getParentDeviceType()).ifPresent(deviceTypeEnum -> {
                List<DictData> dictDataList = stringListMap.getOrDefault(deviceTypeEnum.getIndexTypeCode(), Collections.emptyList());
                for (DictData dictData : dictDataList) {
                    // 写入数据
                    DeviceIndexLog deviceIndexLog = new DeviceIndexLog();
                    deviceIndexLog.setTime(new Date().toInstant());
                    deviceIndexLog.setDeviceId(device.getId());
                    deviceIndexLog.setValue((Math.random() * 100));
                    deviceIndexLog.setIndexId(dictData.getDictValue());
                    deviceIndexLog.setUnit(dictData.getExpand1());
                    deviceIndexLog.setDeviceTypeCode(deviceTypeEnum.getCode());
                    // 数据异步处理类
                    deviceIndexLogAsyncHandler.handle(deviceIndexLog);
                }
            });
        }
        log.debug("LogGenerationTask end");
    }
}
