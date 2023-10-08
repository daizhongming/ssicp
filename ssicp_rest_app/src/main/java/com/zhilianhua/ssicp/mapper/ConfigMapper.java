package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.Config;
import com.zhilianhua.ssicp.entity.request.searchparams.ConfigParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfigMapper {

    PageResultEntity<Config> search(ConfigParams configParams);

    void add(Config config);

    void update(Config config);

    void delete(@Param("id") String id);

    String findBykey(@Param("key") String key);

    List<Config> findAll();
}