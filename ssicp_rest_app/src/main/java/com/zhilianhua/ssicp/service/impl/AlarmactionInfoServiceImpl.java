package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.AlarmactionInfo;
import com.zhilianhua.ssicp.mapper.AlarmactionInfoMapper;
import com.zhilianhua.ssicp.service.AlarmactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmactionInfoServiceImpl implements AlarmactionInfoService {
    @Autowired
    private AlarmactionInfoMapper alarmactionInfoMapper;
    @Override
    public void add(AlarmactionInfo alarmactionInfo) {
        alarmactionInfoMapper.add(alarmactionInfo);
    }
}