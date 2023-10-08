package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.SteamIndexLog;
import com.zhilianhua.ssicp.mapper.SteamIndexLogMapper;
import com.zhilianhua.ssicp.service.SteamIndexLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SteamIndexLogServiceImpl implements SteamIndexLogService {
    @Autowired
    private SteamIndexLogMapper steamIndexLogMapper;



    @Override
    public List<SteamIndexLog> findToday() {
        return steamIndexLogMapper.findToday();
    }
}
