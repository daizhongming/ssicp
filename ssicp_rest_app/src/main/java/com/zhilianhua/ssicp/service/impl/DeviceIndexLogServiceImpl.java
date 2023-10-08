package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceIndexLogSearchParams;
import com.zhilianhua.ssicp.flux.DeviceIndexLogFlux;
import com.zhilianhua.ssicp.service.DeviceIndexLogService;
import com.zhilianhua.ssicp.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DeviceIndexLogServiceImpl implements DeviceIndexLogService {
    @Autowired
    private DeviceIndexLogFlux deviceIndexLogFlux;

    @Override
    public void add(DeviceIndexLog deviceIndexLog) {
        deviceIndexLogFlux.add(deviceIndexLog);
    }

    @Override
    public List<DeviceIndexLog> findIndexLogsHistory(DeviceIndexLogSearchParams deviceIndexLogSearchParams) {
        Date stop = deviceIndexLogSearchParams.getStartTime();
        Date start = DateUtils.daysBefore(7, stop);
        return deviceIndexLogFlux.findByIndexId(start, stop, deviceIndexLogSearchParams.getDeviceId(), deviceIndexLogSearchParams.getIndexId());
    }

    @Override
    public List<DeviceIndexLog> findLast() {
        return deviceIndexLogFlux.findLast();
    }
}