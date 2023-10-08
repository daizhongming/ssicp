package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.DeviceAlarmLog;
import lombok.Data;

import java.util.Date;

@Data
public class DeviceAlarmLogVo extends DeviceAlarmLog {
    private Integer alarmTimes;
    private String alarmReason;
    private String handleDescription;
    private String fileList;
    private String fileListBefore;
    private Date ignoreTime;
}