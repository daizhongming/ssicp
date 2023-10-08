package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author 代忠明
 * @description 设备指标信息表
 * @date 2023-07-06
 */
@Data
public class DeviceAlarm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 指标
     */
    @NotBlank(message = "指标不能为空")
    private String index;

    /**
     * 单位
     */
    @NotBlank(message = "指标不能为空")
    private String unit;

    /**
     * 低报
     */
    @NotNull(message = "低报不能为空")
    private Double lowerAlarm;

    /**
     * 高报
     */
    @NotNull(message = "高报不能为空")
    private Double seniorAlarm;

    /**
     * 高高报
     */
    @NotNull(message = "高高报不能为空")
    private Double seniorSeniorAlarm;

    /**
     * 启停状态
     */
    @NotNull(message = "启停状态不能为空")
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

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
    private Date ignoreTime;

    public DeviceAlarm() {
    }

    private boolean isInIgnoreTimeRange() {
        if (Objects.isNull(ignoreTime)) return false;
        return (ignoreTime.getTime() + 86400000L) > new Date().getTime();
    }

    public Integer getAlarmLevel(Double value) {
        if (isInIgnoreTimeRange()) return 3;
        if (value.doubleValue() < getLowerAlarm().doubleValue()) {
            return 0;
        } else if (value.doubleValue() > getSeniorSeniorAlarm().doubleValue()) {
            return 2;
        } else if (value.doubleValue() > getSeniorAlarm().doubleValue()) {
            return 1;
        }
        return 3;
    }

    public boolean isAlarm(Double value) {
        if (Objects.isNull(value)) return false;
        if (getState().intValue() == 0) return false;
        if (isInIgnoreTimeRange()) return false;
        return value.doubleValue() < getLowerAlarm().doubleValue() || value.doubleValue() > getSeniorAlarm().doubleValue();
    }
}