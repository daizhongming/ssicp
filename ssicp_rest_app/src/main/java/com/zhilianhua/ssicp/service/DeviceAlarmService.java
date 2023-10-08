package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmDeviceVO;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmVo;

import java.util.Date;
import java.util.List;

/**
 * @author 代忠明
 * @description 设备指标信息表
 * @date 2023-07-06
 */
public interface DeviceAlarmService {

    List<DeviceAlarm> findAll();

    DeviceAlarmVo getById(String id);

    void saveBatch(List<DeviceAlarm> deviceAlarms);

    List<DeviceAlarm> selectBydid(String id);

    List<DeviceAlarm> getIndicatortemplate(List<String> deviceAlarmIds);

    void updateBatch(List<DeviceAlarm> deviceAlarmList,String deviceId);

    DeviceAlarm findAlarBydidAndindex(String index, String did);

    void ignore(String deviceId, String indexId, Date ignoreTime);
}