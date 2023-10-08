package com.zhilianhua.ssicp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.zhilianhua.ssicp.mapper")
@EnableAspectJAutoProxy
@EnableAsync
public class SsicpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsicpApplication.class, args);
    }

}
