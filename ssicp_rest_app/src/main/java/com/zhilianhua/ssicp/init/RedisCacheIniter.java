package com.zhilianhua.ssicp.init;

import com.zhilianhua.ssicp.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisCacheIniter implements ApplicationRunner {
    @Autowired
    private DeviceService deviceService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化设备缓存开始");
        deviceService.initDeviceDataCache();
        log.info("初始化设备缓存结束");
    }
}
