package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Config;
import com.zhilianhua.ssicp.entity.request.searchparams.ConfigParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

public interface ConfigService {
    PageResultEntity<Config> findPage(ConfigParams configParams);

    void add(Config config);

    void update(Config config);

    void delete(String id);

    //获取用户默认密码
    String getInitPassword();
    List<Config> findAll();
    String getAiModelServiceUrl();
}