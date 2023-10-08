package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import lombok.Data;

@Data
public class DeviceAlarmVo extends DeviceAlarm {
    private String deviceName;
    private String deviceTypeName;
}