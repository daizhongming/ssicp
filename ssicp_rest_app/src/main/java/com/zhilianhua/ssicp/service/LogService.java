package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Log;
import com.zhilianhua.ssicp.entity.request.searchparams.LogParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

public interface LogService {
    void add(Log logEntity);


    PageResultEntity<Log> search(LogParams logParams);
}
