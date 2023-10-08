package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AlarmactionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private String id;

    /**
    * 原因
    */
    private String resolutionReason;

    /**
    * 简述
    */
    private String summary;

    /**
    * 备注
    */
    private String remarks;

    /**
    * 处理后图片
    */
    private String fileList;

    /**
    * 处理前图片
    */
    private String fileListBefore;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 设备名称
     */
    private String deviceName;
    private Date ignoreTime;

    public AlarmactionInfo() {
    }

}