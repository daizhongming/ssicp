package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.Config;
import com.zhilianhua.ssicp.entity.request.searchparams.ConfigParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.ConfigMapper;
import com.zhilianhua.ssicp.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {


    @Value("${config.default.password}")
    private String defaultPassword;
    private static final String USER_INIT_PASSWORD_KEY = "user.initPassword";
    private static final String AI_MODEL_SERVICE_URL_KEY = "aiModelService.url";

    @Value("${config.aiModelService.url}")
    private String modelServiceUrl;
    @Autowired
    private ConfigMapper configMapper;

    @Override
    public PageResultEntity<Config> findPage(ConfigParams configParams) {
        return configMapper.search(configParams);
    }

    @Override
    public void add(Config config) {
        configMapper.add(config);
    }

    @Override
    public void update(Config config) {
        configMapper.update(config);
    }

    @Override
    public void delete(String id) {
        configMapper.delete(id);
    }

    @Override
    public String getInitPassword() {
        return getStringConfig(USER_INIT_PASSWORD_KEY, defaultPassword);
    }

    @Override
    public String getAiModelServiceUrl() {
        return getStringConfig(AI_MODEL_SERVICE_URL_KEY, modelServiceUrl);
    }

    @Override
    public List<Config> findAll() {
        return configMapper.findAll();
    }


    private String getStringConfig(String key, String defaultValue) {
        String value = configMapper.findBykey(key);
        return StringUtils.isBlank(value) ? defaultValue : value;
    }
}
