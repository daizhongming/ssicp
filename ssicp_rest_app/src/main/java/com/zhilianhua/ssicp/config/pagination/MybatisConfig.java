package com.zhilianhua.ssicp.config.pagination;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {
    @Autowired
    private PaginationInterceptor paginationInterceptor;

    @Bean
    public Interceptor[] mybatisInterceptors() {
        return new Interceptor[]{paginationInterceptor};
    }
}
