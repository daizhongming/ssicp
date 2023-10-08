package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.DeviceAbnormalLog;
import com.zhilianhua.ssicp.entity.response.DeviceAbnormalLogVO;

import java.util.List;

public interface DeviceAbnormalLogService {

    List<DeviceAbnormalLogVO> searchByeid(String id);

    void update(DeviceAbnormalLog deviceAbnormalLog);

    void add(DeviceAbnormalLog deviceAbnormalLog);
}