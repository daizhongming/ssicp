package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.controller.share.ShareControllerHandler;
import com.zhilianhua.ssicp.entity.request.DeviceAlarmLogHandleRequest;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogDetailsSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogSearchParams;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmLogAlarmDetailsVo;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmLogHandleDetailsVo;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmLogVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DeviceAlarmLogService;
import com.zhilianhua.ssicp.utils.RequestUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/deviceAlarmLogs")
@Privilege("报警管理")
public class DeviceAlarmLogController {
    @Autowired
    private DeviceAlarmLogService deviceAlarmLogService;
    @Autowired
    private ShareControllerHandler shareControllerHandler;

    @GetMapping("")
    @Privilege("搜索")
    public ResponseEntity<PageResultEntity<DeviceAlarmLogVo>> search(@Valid DeviceAlarmLogSearchParams deviceAlarmLogSearchParams) {
        return ResponseEntityUtils.success(deviceAlarmLogService.search(deviceAlarmLogSearchParams));
    }

    @GetMapping("/alarmDetails")
    @Privilege("报警详情")
    public ResponseEntity<List<DeviceAlarmLogAlarmDetailsVo>> findAlarmDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams) {
        return shareControllerHandler.findAlarmDetails(deviceAlarmLogDetailsSearchParams);
    }

    @GetMapping("/handleDetails")
    @Privilege("处理详情")
    public ResponseEntity<List<DeviceAlarmLogHandleDetailsVo>> findHandleDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams) {
        return ResponseEntityUtils.success(deviceAlarmLogService.findHandleDetails(deviceAlarmLogDetailsSearchParams));
    }

    @PutMapping("/handleStatus")
    @Privilege(value = "处理")
    public ResponseEntity handle(@RequestBody @Valid DeviceAlarmLogHandleRequest deviceAlarmLogHandleRequest
            , @RequestHeader(RequestUtils.USER_NAME_KEY) String userName
            , @RequestHeader(RequestUtils.NICK_NAME_KEY) String userNickName) {
        return shareControllerHandler.handle(deviceAlarmLogHandleRequest, userName, userNickName);
    }
}