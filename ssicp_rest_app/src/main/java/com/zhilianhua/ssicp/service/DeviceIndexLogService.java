package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceIndexLogSearchParams;

import java.util.List;

public interface DeviceIndexLogService {

    void add(DeviceIndexLog deviceIndexLog);

    List<DeviceIndexLog> findIndexLogsHistory(DeviceIndexLogSearchParams deviceIndexLogSearchParams);

    List<DeviceIndexLog> findLast();
}
