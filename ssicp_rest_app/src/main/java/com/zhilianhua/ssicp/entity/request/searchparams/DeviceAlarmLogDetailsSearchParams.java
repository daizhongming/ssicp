package com.zhilianhua.ssicp.entity.request.searchparams;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DeviceAlarmLogDetailsSearchParams {
    private String deviceId;
    private String indexId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date handleTime;
}