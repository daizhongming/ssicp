package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmDeviceVO;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmVo;
import com.zhilianhua.ssicp.mapper.DeviceAlarmMapper;
import com.zhilianhua.ssicp.service.DeviceAlarmService;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 代忠明
 * @description 设备指标信息表
 * @date 2023-07-06
 */
@Service
public class DeviceAlarmServiceImpl implements DeviceAlarmService {
    @Autowired
    private DeviceAlarmMapper deviceAlarmMapper;

    @Override
    public List<DeviceAlarm> findAll() {
        return deviceAlarmMapper.findAll();
    }

    @Override
    public DeviceAlarmVo getById(String id) {
        return deviceAlarmMapper.getById(id);
    }

    @Override
    public void saveBatch(List<DeviceAlarm> deviceAlarms) {
        deviceAlarmMapper.saveBatch(deviceAlarms);
    }

    @Override
    public List<DeviceAlarm> selectBydid(String id) {
        return deviceAlarmMapper.selectBydid(id);
    }

    @Override
    public List<DeviceAlarm> getIndicatortemplate(List<String> deviceAlarmIds) {
        return deviceAlarmMapper.getIndicatortemplate(deviceAlarmIds);
    }

    @Override
    public void updateBatch(List<DeviceAlarm> deviceAlarmList, String deviceId) {
        //删除指标
        deviceAlarmMapper.deleteBydId(deviceId);
        if (deviceAlarmList != null && deviceAlarmList.size() > 0) {
            //添加指标
            deviceAlarmList.forEach(deviceAlarm -> {
                deviceAlarm.setId(IdUtils.uuid());
                deviceAlarm.setDeviceId(deviceId);
            });
            deviceAlarmMapper.saveBatch(deviceAlarmList);
        }
    }

    @Override
    public DeviceAlarm findAlarBydidAndindex(String index, String did) {
        return deviceAlarmMapper.findAlarBydidAndindex(index, did);
    }

    @Override
    public void ignore(String deviceId, String indexId, Date ignoreTime) {
        deviceAlarmMapper.ignore(deviceId,indexId,ignoreTime);
    }
}