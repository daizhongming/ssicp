package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.DeviceAbnormalLog;
import com.zhilianhua.ssicp.entity.response.DeviceAbnormalLogVO;
import com.zhilianhua.ssicp.mapper.DeviceAbnormalLogMapper;
import com.zhilianhua.ssicp.service.DeviceAbnormalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceAbnormalLogServiceImpl implements DeviceAbnormalLogService {
    @Autowired
    private DeviceAbnormalLogMapper deviceAbnormalLogMapper;

    @Override
    public List<DeviceAbnormalLogVO> searchByeid(String id) {
        return deviceAbnormalLogMapper.searchByeid(id);
    }

    @Override
    public void update(DeviceAbnormalLog deviceAbnormalLog) {
        deviceAbnormalLogMapper.update(deviceAbnormalLog);
    }

    @Override
    public void add(DeviceAbnormalLog deviceAbnormalLog) {
        deviceAbnormalLogMapper.add(deviceAbnormalLog);
    }
}