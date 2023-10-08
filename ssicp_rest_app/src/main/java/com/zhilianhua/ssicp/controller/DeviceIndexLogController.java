package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.controller.share.ShareControllerHandler;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceIndexLogSearchParams;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DeviceIndexLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/deviceIndexLogs")
@Privilege("指标记录管理")
public class DeviceIndexLogController {
    @Autowired
    private DeviceIndexLogService deviceIndexLogService;
    @Autowired
    private ShareControllerHandler shareControllerHandler;


    @GetMapping("/indexLogsHistory")
    @Privilege("历史指标趋势")
    public ResponseEntity<List<DeviceIndexLog>> findIndexLogsHistory(DeviceIndexLogSearchParams deviceIndexLogSearchParams) {
        return shareControllerHandler.findIndexLogsHistory(deviceIndexLogSearchParams);
    }
}