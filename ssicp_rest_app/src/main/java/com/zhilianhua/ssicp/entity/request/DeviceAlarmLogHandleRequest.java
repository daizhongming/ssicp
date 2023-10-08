package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class DeviceAlarmLogHandleRequest {
    private String deviceId;
    private String indexId;
    private Integer handleStatus;
    @NotBlank(message = "原因不能为空")
    private String alarmReason;
    private Date ignoreTime;
    private String handleDescription;
    private String handleUserName;
    private String handleUserNickName;
    private String fileList;
    private String fileListBefore;
    private String alarmActionInfoId;
}
