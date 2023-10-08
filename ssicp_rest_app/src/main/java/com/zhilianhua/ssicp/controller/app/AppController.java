package com.zhilianhua.ssicp.controller.app;

import com.zhilianhua.ssicp.controller.share.ShareControllerHandler;
import com.zhilianhua.ssicp.entity.app.TodosVO;
import com.zhilianhua.ssicp.entity.db.FileInfo;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.DeviceAlarmLogHandleRequest;
import com.zhilianhua.ssicp.entity.request.LoginEntity;
import com.zhilianhua.ssicp.entity.request.ManualInspectionOutcomeAdd;
import com.zhilianhua.ssicp.entity.request.app.ReadMessageParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogDetailsSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceIndexLogSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.DeviceIndexLogVo;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.AppService;
import com.zhilianhua.ssicp.utils.RequestUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestMapping("/app")
@RestController
@Privilege("app")
public class AppController {
    @Autowired
    private ShareControllerHandler shareControllerHandler;
    @Autowired
    private AppService appService;

    @PostMapping("/login")
    @Privilege(value = "登录")
    public ResponseEntity<String> login(@Valid @RequestBody LoginEntity loginEntity) {
        return shareControllerHandler.login(loginEntity);
    }

    @GetMapping("/todos")
    @Privilege(value = "查询代办列表")
    public ResponseEntity todos(@RequestHeader(RequestUtils.USER_NAME_KEY) String userName) {
        List<TodosVO> todosVOList = appService.todos(userName);
        return ResponseEntityUtils.success(todosVOList);
    }

    @GetMapping("/messages")
    @Privilege(value = "查询消息")
    public ResponseEntity messages(@RequestHeader(RequestUtils.USER_NAME_KEY) String userName) {
        return ResponseEntityUtils.success(appService.messages(userName));
    }


    @PutMapping("/messages/read")
    @Privilege(value = "消息已读")
    public ResponseEntity readMessage(@RequestBody ReadMessageParams readMessageParams, @RequestHeader(RequestUtils.USER_NAME_KEY) String userName) {
        if (readMessageParams.getAllRead().intValue() == 0 && Objects.isNull(readMessageParams.getMessageId())) {
            throw BusinessException.instance("参数不能为空");
        }
        readMessageParams.setUserName(userName);
        appService.readMessage(readMessageParams);
        return ResponseEntityUtils.success();
    }

    @GetMapping("/me")
    @Privilege(value = "查询我的信息")
    public ResponseEntity<LoginMessageVo> me(@RequestHeader(value = RequestUtils.USER_NAME_KEY,required = false) String userName) {
        return ResponseEntityUtils.success(appService.findByName(userName));
    }


    @GetMapping("/statistics/productionAndConsumption")
    @Privilege(value = "产用气统计")
    public ResponseEntity productionAndConsumption() {
        return ResponseEntityUtils.success(appService.productionAndConsumption());
    }

    @GetMapping("/statistics/messages")
    @Privilege(value = "消息统计")
    public ResponseEntity<List<MessageAnalyseEntity>> messagesAnalyse(@RequestHeader(RequestUtils.USER_NAME_KEY) String userName) {
        return ResponseEntityUtils.success(appService.messagesAnalyse(userName));
    }

    @GetMapping("/statistics/index/alarms")
    @Privilege(value = "指标报警统计")
    public ResponseEntity<List<AnalyseEntity>> indexAlarmAnalyse() {
        return ResponseEntityUtils.success(appService.indexAlarmAnalyse());
    }

    @GetMapping("/statistics/device/alarms")
    @Privilege(value = "装置报警统计")
    public ResponseEntity<List<AnalyseEntity>> deviceAlarmAnalyse() {
        return ResponseEntityUtils.success(appService.deviceAlarmAnalyse());
    }

    @GetMapping("/statistics/device/count")
    @Privilege(value = "设备个数统计")
    public ResponseEntity<List<AnalyseEntity>> deviceCountAnalyse() {
        return ResponseEntityUtils.success(appService.deviceCountAnalyse());
    }

    @GetMapping("/statistics/todos")
    @Privilege(value = "代办统计")
    public ResponseEntity<List<TodoAnalyseEntity>> todosAnalyse(@RequestHeader(value = RequestUtils.USER_NAME_KEY) String userName) {
        return ResponseEntityUtils.success(appService.todoAnalyse(userName));
    }

    @PostMapping("/files")
    @ResponseBody
    @Privilege(value = "上传文件")
    public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile file) {
        return shareControllerHandler.upload(file);
    }

    @PostMapping("/files/multi")
    @ResponseBody
    @Privilege(value = "上传多文件")
    public ResponseEntity<List<FileInfo>> uploadMulti(@RequestParam("files") MultipartFile[] files) {
        List<MultipartFile> collect = Arrays.stream(files).filter(file -> !file.isEmpty()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return ResponseEntityUtils.error("文件不能为空");
        }
        return ResponseEntityUtils.success(appService.uploadMulti(collect));
    }

    @GetMapping("/files/{fileCode}")
    @Privilege(value = "下载文件")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileCode") String fileCode) throws IOException {
        return shareControllerHandler.downloadFile(fileCode);
    }

    @GetMapping("/deviceIndexLogs/indexLogsHistory")
    @Privilege("历史指标趋势")
    public ResponseEntity<List<DeviceIndexLog>> findIndexLogsHistory(DeviceIndexLogSearchParams deviceIndexLogSearchParams) {
        return shareControllerHandler.findIndexLogsHistory(deviceIndexLogSearchParams);
    }


    @PutMapping("/alarmHandle/handleStatus")
    @Privilege(value = "处理")
    public ResponseEntity handle(@RequestBody @Valid DeviceAlarmLogHandleRequest deviceAlarmLogHandleRequest
            , @RequestHeader(RequestUtils.USER_NAME_KEY) String userName
            , @RequestHeader(RequestUtils.NICK_NAME_KEY) String userNickName) {
        return shareControllerHandler.handle(deviceAlarmLogHandleRequest, userName, userNickName);
    }


    @GetMapping("/deviceIndexLogs")
    @Privilege("实时监测数据")
    public ResponseEntity<List<DeviceIndexLogVo>> deviceIndexLogs() {
        return ResponseEntityUtils.success(appService.deviceIndexLogs());
    }


    @GetMapping("/alarmHandle/data")
    @Privilege("获取最近的指标报警处理记录")
    public ResponseEntity<DeviceAlarmLogVo> getAlarmHandleData(String deviceId, String indexId) {
        return ResponseEntityUtils.success(appService.getAlarmHandleData(deviceId,indexId));
    }

    @GetMapping("/alarmHandle/alarmDetails")
    @Privilege("报警详情")
    public ResponseEntity<List<DeviceAlarmLogAlarmDetailsVo>> findAlarmDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams) {
        return shareControllerHandler.findAlarmDetails(deviceAlarmLogDetailsSearchParams);
    }


    @GetMapping("/statistics/inspection/manual/month")
    @Privilege(value = "近一个月人工巡检统计")
    public ResponseEntity<AnalyseManualInspection> monthManualInspectionAnalyse() {
        return ResponseEntityUtils.success(appService.monthManualInspectionAnalyse());
    }

    @GetMapping("/statistics/inspection/manual/all")
    @Privilege(value = "所有人工巡检统计")
    public ResponseEntity<AnalyseManualInspection> allManualInspectionAnalyse() {
        return ResponseEntityUtils.success(appService.allManualInspectionAnalyse());
    }

    @GetMapping("/statistics/inspection")
    @Privilege(value = "巡检统计")
    public ResponseEntity<List<AnalyseInspection>> inspectionAnalyse() {
        return ResponseEntityUtils.success(appService.inspectionAnalyse());
    }

    @GetMapping("/inspection/halfMonth")
    @Privilege(value = "最近半月智能巡检")
    public ResponseEntity<List<IntelligentInspectionOutcomeAppVo>> findHalfMonthInspection() {
        return ResponseEntityUtils.success(appService.findHalfMonthInspection());
    }

    @GetMapping("/manualInspection/halfMonth")
    @Privilege(value = "最近半月人工能巡检")
    public ResponseEntity<List<ManualInspectionOutcomeAppVo>> findHalfMonthManualInspection() {
        return ResponseEntityUtils.success(appService.findHalfMonthManualInspection());
    }


    @GetMapping("/inspection/outcome/{planCode}")
    @Privilege(value = "查询智能巡检结果详情")
    public ResponseEntity<IntelligentInspectionOutcomeAppVo> getInspectionData(@PathVariable String planCode) {
        return ResponseEntityUtils.success(appService.getInspectionData(planCode));
    }


    @GetMapping("/manualInspection/outcome/{planCode}")
    @Privilege(value = "查询人工巡检结果详情")
    public ResponseEntity<ManualInspectionOutcomeAppVo> getManualInspectionData(@PathVariable String planCode) {
        return ResponseEntityUtils.success(appService.getManualInspectionData(planCode));
    }


    @PutMapping("/manualInspection/disposeWrite")
    @Privilege(value = "人工巡检结果结果录入")
    public ResponseEntity manualInspectionDisposeWrite(@RequestBody @Valid ManualInspectionOutcomeAdd manualInspectionOutcomeAdd) {
        appService.manualInspectionDisposeWrite(manualInspectionOutcomeAdd);
        return ResponseEntityUtils.success();
    }
}
