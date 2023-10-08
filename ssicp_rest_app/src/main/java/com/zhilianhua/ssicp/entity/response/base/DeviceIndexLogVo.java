package com.zhilianhua.ssicp.entity.response.base;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceIndexLogVo {

    private String indexName;
    private String deviceType;
    private String deviceName;
    private Double indexValue;
    private String unit;
    private Date createTime;
    private Integer alarmFlag;
    private Integer alarmLevel;
    private Integer handleFlag;
    private Date handleTime;
    private String indexId;
    private String deviceId;

}
