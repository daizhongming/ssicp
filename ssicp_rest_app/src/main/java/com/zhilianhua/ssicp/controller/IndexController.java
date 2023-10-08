package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.controller.share.ShareControllerHandler;
import com.zhilianhua.ssicp.entity.request.LoginEntity;
import com.zhilianhua.ssicp.entity.response.AnalyseVo;
import com.zhilianhua.ssicp.entity.response.LoginMessageVo;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DeviceAlarmLogService;
import com.zhilianhua.ssicp.service.IndexService;
import com.zhilianhua.ssicp.service.UserService;
import com.zhilianhua.ssicp.utils.RequestUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/index")
@Privilege("首页管理")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @Autowired
    private UserService userService;
    @Autowired
    private ShareControllerHandler shareControllerHandler;

    @PostMapping("/login")
    @Privilege(value = "登录")
    public ResponseEntity<String> login(@Valid @RequestBody LoginEntity loginEntity) {
        return shareControllerHandler.login(loginEntity);
    }

    @GetMapping("/messages")
    @Privilege(value = "查询基本信息")
    public ResponseEntity<LoginMessageVo> getMessage(@RequestHeader(value = RequestUtils.USER_NAME_KEY, required = false) String userName) {
        return ResponseEntityUtils.success(indexService.getMessage(userName));
    }

    @Autowired
    private DeviceAlarmLogService deviceAlarmLogService;

    @GetMapping("/analyseAlarmByIndexName")
    @Privilege("指标统计报警")
    public ResponseEntity<List<AnalyseVo>> analyseAlarmByIndexName() {
        return ResponseEntityUtils.success(deviceAlarmLogService.analyseByIndexName());
    }

    @GetMapping("/analyseAlarmByMonth")
    @Privilege("月份统计报警")
    public ResponseEntity<List<AnalyseVo>> analyseAlarmByMonth() {
        return ResponseEntityUtils.success(deviceAlarmLogService.analyseByMonth());
    }

    @GetMapping("/countDeviceAnomalies")
    @Privilege("设备异常统计")
    public ResponseEntity<List<AnalyseVo>> countDeviceAnomalies() {
        return ResponseEntityUtils.success(deviceAlarmLogService.countDeviceAnomalies());
    }

    @GetMapping("/analyseAnomaliesByMonth")
    @Privilege("月份统计异常")
    public ResponseEntity<List<AnalyseVo>> analyseAnomaliesByMonth() {
        return ResponseEntityUtils.success(deviceAlarmLogService.analyseAnomaliesByMonth());
    }
}
