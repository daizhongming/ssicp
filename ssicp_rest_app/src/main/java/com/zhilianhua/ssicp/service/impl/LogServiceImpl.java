package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Log;
import com.zhilianhua.ssicp.entity.request.searchparams.LogParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.LogMapper;
import com.zhilianhua.ssicp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void add(Log logEntity) {
        logMapper.add(logEntity);
    }

    @Override
    public PageResultEntity<Log> search(LogParams logParams) {
        return logMapper.search(logParams);
    }
}
