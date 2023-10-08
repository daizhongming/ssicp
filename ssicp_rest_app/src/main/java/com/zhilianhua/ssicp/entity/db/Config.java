package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class Config {

    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    private Integer configId;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 系统内置（y是 n否）
     */
    private String configType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标记（0.未删除 1.删除）
     */
    private Integer deleteFlag;

    public Config() {
    }

}