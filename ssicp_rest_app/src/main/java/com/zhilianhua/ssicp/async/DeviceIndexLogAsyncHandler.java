package com.zhilianhua.ssicp.async;

import com.zhilianhua.ssicp.entity.db.Device;
import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.db.DeviceAlarmLog;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.response.DeviceDataVo;
import com.zhilianhua.ssicp.redis.DeviceRedisService;
import com.zhilianhua.ssicp.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/***
 * 收到传感器日志后的异步处理器
 */
@Component
@Slf4j
public class DeviceIndexLogAsyncHandler {
    @Autowired
    private DeviceIndexLogService deviceIndexLogService;
    @Autowired
    private DeviceAlarmLogService deviceAlarmLogService;
    @Autowired
    private DeviceAlarmService deviceAlarmService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private DeviceRedisService deviceRedisService;

    @Async
    public void handle(DeviceIndexLog deviceIndexLog) {
        // 写fluxDB
        deviceIndexLogService.add(deviceIndexLog);
        DeviceDataVo.RealTimeData realTimeData = new DeviceDataVo.RealTimeData();
        realTimeData.setIndexId(deviceIndexLog.getIndexId());
        realTimeData.setTime(Date.from(deviceIndexLog.getTime()));
        realTimeData.setIndexValue(deviceIndexLog.getValue());
        realTimeData.setUnit(deviceIndexLog.getUnit());
        realTimeData.setAlarmLevel(3);
        DeviceAlarm deviceAlarm = deviceAlarmService.findAlarBydidAndindex(deviceIndexLog.getIndexId(), deviceIndexLog.getDeviceId());
        Device device = deviceService.load(deviceIndexLog.getDeviceId());
        Optional<String> deviceTypeName = DeviceTypeEnum.getNameByParentDeviceType(device.getParentDeviceType());
        // 拿到指标相关数据
        if (Objects.nonNull(deviceAlarm) && Objects.nonNull(device) && deviceTypeName.isPresent() && deviceAlarm.isAlarm(deviceIndexLog.getValue())) {
            // 写入报警数据
            DeviceAlarmLog deviceAlarmLog = new DeviceAlarmLog();
            deviceAlarmLog.setAlarmValue(deviceIndexLog.getValue());
            deviceAlarmLog.setDeviceId(deviceIndexLog.getDeviceId());
            deviceAlarmLog.setIndexId(deviceIndexLog.getIndexId());
            deviceAlarmLog.setIndexName(deviceIndexLog.getIndexId());
            deviceAlarmLog.setDeviceName(device.getDeviceName());
            deviceAlarmLog.setDeviceTypeName(deviceTypeName.get());
            deviceAlarmLog.setAlarmLevel(deviceAlarm.getAlarmLevel(deviceIndexLog.getValue()));
            deviceAlarmLog.setUnit(deviceIndexLog.getUnit());
            deviceAlarmLog.setLowerAlarm(deviceAlarm.getLowerAlarm());
            deviceAlarmLog.setSeniorAlarm(deviceAlarm.getSeniorAlarm());
            deviceAlarmLog.setSeniorSeniorAlarm(deviceAlarm.getSeniorSeniorAlarm());
            deviceAlarmLog.setStartTime(Date.from(deviceIndexLog.getTime()));
            // 写报警
            deviceAlarmLogService.add(deviceAlarmLog);
            // 写消息
            messageService.addAlarm(deviceAlarmLog);
            realTimeData.setAlarmLevel(deviceAlarm.getAlarmLevel(deviceIndexLog.getValue()));
            realTimeData.setLowerAlarm(deviceAlarm.getLowerAlarm());
            realTimeData.setSeniorAlarm(deviceAlarm.getSeniorAlarm());
            realTimeData.setSeniorSeniorAlarm(deviceAlarm.getSeniorSeniorAlarm());
        }
        // 写入缓存
        deviceRedisService.setDeviceRealTimeData(deviceIndexLog.getDeviceId(), deviceIndexLog.getIndexId(), realTimeData);
    }
}
