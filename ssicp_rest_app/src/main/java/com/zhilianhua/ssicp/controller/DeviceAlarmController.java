package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.service.DeviceAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代忠明
 * @description 设备指标信息表
 * @date 2023-07-06
 */
@RestController
@RequestMapping(value = "/deviceAlarms")
public class DeviceAlarmController {
    private DeviceAlarmService deviceAlarmService;
}