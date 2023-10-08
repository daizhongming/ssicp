package com.zhilianhua.ssicp.controller.share;

import com.zhilianhua.ssicp.entity.db.FileInfo;
import com.zhilianhua.ssicp.entity.db.User;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.DeviceAlarmLogHandleRequest;
import com.zhilianhua.ssicp.entity.request.LoginEntity;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogDetailsSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceIndexLogSearchParams;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmLogAlarmDetailsVo;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.FileUtils;
import com.zhilianhua.ssicp.utils.JwtUtils;
import com.zhilianhua.ssicp.utils.RequestUtils;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Component
public class ShareControllerHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DeviceIndexLogService deviceIndexLogService;
    @Autowired
    private DeviceAlarmLogService deviceAlarmLogService;

    public ResponseEntity<String> login(LoginEntity loginEntity) {
        User user = userService.findByName(loginEntity.getName());
        if (Objects.isNull(user) || !BCrypt.checkpw(loginEntity.getPassword(), user.getPassword())) {
            return ResponseEntityUtils.error("登录失败");
        }
        String s = JwtUtils.generateToken(user);
        return ResponseEntityUtils.success(s);
    }

    public ResponseEntity<FileInfo> upload(MultipartFile file) {
        if (!file.isEmpty()) {
            return ResponseEntityUtils.success(fileService.upload(file));
        } else {
            return ResponseEntityUtils.error("文件不能为空");
        }
    }

    public ResponseEntity<Resource> downloadFile(String fileCode) throws IOException {
        FileInfo fileInfo = fileService.findByCode(fileCode);
        if (Objects.isNull(fileInfo)) {
            return ResponseEntityUtils.error("文件不存在");
        }
        String allFilePath = FileUtils.getAllFilePath(fileInfo.getCode());
        // 获取要下载的文件路径
        Path filePath = Paths.get(allFilePath);
        // 读取文件资源
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            return ResponseEntityUtils.error("文件不存在");
        }

        // 配置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileInfo.getName(), StandardCharsets.UTF_8.toString()));

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }


    public ResponseEntity<List<DeviceIndexLog>> findIndexLogsHistory(DeviceIndexLogSearchParams deviceIndexLogSearchParams) {
        return ResponseEntityUtils.success(deviceIndexLogService.findIndexLogsHistory(deviceIndexLogSearchParams));
    }

    public ResponseEntity<List<DeviceAlarmLogAlarmDetailsVo>> findAlarmDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams) {
        return ResponseEntityUtils.success(deviceAlarmLogService.findAlarmDetails(deviceAlarmLogDetailsSearchParams));
    }



    public ResponseEntity handle(DeviceAlarmLogHandleRequest deviceAlarmLogHandleRequest,
            String userName
            , String userNickName) {
        deviceAlarmLogHandleRequest.setHandleUserName(userName);
        deviceAlarmLogHandleRequest.setHandleUserNickName(userNickName);
        checkAndPreHandleParams(deviceAlarmLogHandleRequest);
        deviceAlarmLogService.handle(deviceAlarmLogHandleRequest);
        return ResponseEntityUtils.success();
    }

    private void checkAndPreHandleParams(DeviceAlarmLogHandleRequest deviceAlarmLogHandleRequest) {
        if (deviceAlarmLogHandleRequest.getHandleStatus() == 1) {
            // 处理
            if (StringUtils.isBlank(deviceAlarmLogHandleRequest.getHandleDescription())) {
                throw BusinessException.instance("处理简述不能为空");
            }
            if (StringUtils.isBlank(deviceAlarmLogHandleRequest.getFileListBefore())) {
                throw BusinessException.instance("处理前图片不能为空");
            }
            if (StringUtils.isBlank(deviceAlarmLogHandleRequest.getFileList())) {
                throw BusinessException.instance("处理后图片不能为空");
            }
            deviceAlarmLogHandleRequest.setIgnoreTime(null);
        } else {
            // 忽略
            deviceAlarmLogHandleRequest.setHandleDescription(null);
            deviceAlarmLogHandleRequest.setFileList(null);
            deviceAlarmLogHandleRequest.setFileListBefore(null);
        }
    }
}
