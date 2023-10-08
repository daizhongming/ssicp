package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @description 设备表
 * @date 2023-08-23
 */
@Data
public class Device implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 设备名称
     */
    @NotBlank(message = "设备名称不能为空")
    private String deviceName;

    /**
     * 设备父型号（device_type）
     */
    @NotBlank(message = "设备父型号不能为空")
    private String parentDeviceType;

    /**
     * 设备子类型
     */
    @NotBlank(message = "设备类型不能为空")
    private String deviceType;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空")
    private String deviceCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createdByName;

    /**
     * 修改人
     */
    private String updatedByName;

    /**
     * 删除标记（0.未删除 1.删除）
     */
    private Integer deleteFlag;

    /**
     * 备注
     */
    private String comments;

    //枚举
    public static String YIBIAO = "1";
    public static String FAMEN = "2";
    public static String SHUSHUIFA = "3";
    public static String GUANDUAN = "4";
    public static String CHANQIDIAN = "5";
    public static String YONGQIDIAN = "6";
    public static String BUCHANGQI = "7";
    public static String SANTONG = "8";
    public static String WANTOU = "9";
    public static String CHANYONGQI = "10";
}