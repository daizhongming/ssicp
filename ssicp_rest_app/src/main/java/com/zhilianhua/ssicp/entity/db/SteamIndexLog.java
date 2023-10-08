package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class SteamIndexLog {
    private Long id;
    private String deviceId;
    private Double instantaneousValue;
    private Double accumulationValue;
    private Date createTime;
    private Integer inOutFlag;
}