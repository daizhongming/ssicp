package com.zhilianhua.ssicp.task.impl;

import com.zhilianhua.ssicp.async.DeviceIndexLogAsyncHandler;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.response.DeviceDataVo;
import com.zhilianhua.ssicp.entity.response.DeviceGasPointVO;
import com.zhilianhua.ssicp.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SteamLogGenerationJob implements Job {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceIndexLogAsyncHandler deviceIndexLogAsyncHandler;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        List<String> productions = deviceService.findAllProduction().stream().map(DeviceGasPointVO::getId).collect(Collectors.toList());
        List<String> consumptions = deviceService.findAllConsumption().stream().map(DeviceGasPointVO::getId).collect(Collectors.toList());
        log.debug("SteamLogGenerationJob start");
        Date now = new Date();
        double productionSpeed = (12.0d + Math.random()) / 3600000d / productions.size();
        double consumptionSpeed = (10.0d + Math.random()) / 3600000d / consumptions.size();
        productions.forEach(production -> {
            // 温度
            DeviceIndexLog deviceIndexLog = new DeviceIndexLog();
            deviceIndexLog.setTime(new Date().toInstant());
            deviceIndexLog.setDeviceId(production);
            deviceIndexLog.setValue((Math.random() * 100));
            deviceIndexLog.setIndexId("温度");
            deviceIndexLog.setUnit("℃");
            deviceIndexLog.setDeviceTypeCode(DeviceTypeEnum.PRODUCTION.getCode());
            deviceIndexLogAsyncHandler.handle(deviceIndexLog);
            // 压力
            DeviceIndexLog deviceIndexLog2 = new DeviceIndexLog();
            deviceIndexLog2.setTime(new Date().toInstant());
            deviceIndexLog2.setDeviceId(production);
            deviceIndexLog2.setValue((Math.random() * 100));
            deviceIndexLog2.setIndexId("压力");
            deviceIndexLog2.setUnit("MPa");
            deviceIndexLog2.setDeviceTypeCode(DeviceTypeEnum.PRODUCTION.getCode());
            deviceIndexLogAsyncHandler.handle(deviceIndexLog2);
            // 流量
            DeviceIndexLog deviceIndexLog3 = new DeviceIndexLog();
            deviceIndexLog3.setTime(new Date().toInstant());
            deviceIndexLog3.setDeviceId(production);
            deviceIndexLog3.setValue(productionSpeed * 3600000d);
            deviceIndexLog3.setIndexId("流量");
            deviceIndexLog3.setUnit("t/h");
            deviceIndexLog3.setDeviceTypeCode(DeviceTypeEnum.PRODUCTION.getCode());
            deviceIndexLogAsyncHandler.handle(deviceIndexLog3);
            // 累计流量
            DeviceIndexLog deviceIndexLog4 = new DeviceIndexLog();
            deviceIndexLog4.setTime(new Date().toInstant());
            deviceIndexLog4.setDeviceId(production);
            DeviceDataVo deviceData = deviceService.getDeviceData(production);
            if (Objects.isNull(deviceData) || Objects.isNull(deviceData.getRealTimeData()) || !deviceData.getRealTimeData().containsKey("累计流量")) {
                deviceIndexLog4.setValue(0d);
            } else {

                deviceIndexLog4.setValue(deviceData.getRealTimeData().get("累计流量").getIndexValue() + productionSpeed * (now.getTime() - deviceData.getRealTimeData().get("累计流量").getTime().getTime()));
            }
            deviceIndexLog4.setIndexId("累计流量");
            deviceIndexLog4.setUnit("t");
            deviceIndexLog4.setDeviceTypeCode(DeviceTypeEnum.PRODUCTION.getCode());
            deviceIndexLogAsyncHandler.handle(deviceIndexLog4);
        });
        consumptions.forEach(consumption -> {
            // 流量
            DeviceIndexLog deviceIndexLog3 = new DeviceIndexLog();
            deviceIndexLog3.setTime(new Date().toInstant());
            deviceIndexLog3.setDeviceId(consumption);
            deviceIndexLog3.setValue(consumptionSpeed * 3600000d);
            deviceIndexLog3.setIndexId("流量");
            deviceIndexLog3.setUnit("t/h");
            deviceIndexLog3.setDeviceTypeCode(DeviceTypeEnum.CONSUMPTION.getCode());
            deviceIndexLogAsyncHandler.handle(deviceIndexLog3);
            // 累计流量
            DeviceIndexLog deviceIndexLog4 = new DeviceIndexLog();
            deviceIndexLog4.setTime(new Date().toInstant());
            deviceIndexLog4.setDeviceId(consumption);
            DeviceDataVo deviceData = deviceService.getDeviceData(consumption);
            if (Objects.isNull(deviceData) || Objects.isNull(deviceData.getRealTimeData()) || !deviceData.getRealTimeData().containsKey("累计流量")) {
                deviceIndexLog4.setValue(0d);
            } else {
                deviceIndexLog4.setValue(deviceData.getRealTimeData().get("累计流量").getIndexValue() + consumptionSpeed * (now.getTime() - deviceData.getRealTimeData().get("累计流量").getTime().getTime()));
            }
            deviceIndexLog4.setIndexId("累计流量");
            deviceIndexLog4.setUnit("t");
            deviceIndexLog4.setDeviceTypeCode(DeviceTypeEnum.CONSUMPTION.getCode());
            deviceIndexLogAsyncHandler.handle(deviceIndexLog4);
        });
        log.debug("SteamLogGenerationJob end");
    }
}
