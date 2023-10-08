package com.zhilianhua.ssicp.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class DeviceAlarmLogHandleDetailsVo {
    private Date handleTime;
    private Integer handleStatus;
    private String handleUserNickName;

}