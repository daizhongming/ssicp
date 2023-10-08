package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InspectionExceptionVO {
    /**
     * 主键
     */
    private String id;

    /**
     * 巡检任务名称
     */
    private String planName;

    /**
     * 管线名称
     */
    private String pipelineName;

    /**
     * 异常位置
     */
    private String abnormalLocation;

    /**
     * 处理状态（0.未处理 1.已处理 3.已忽略）
     */
    private Integer disposeState;

    /**
     * 原因
     */
    private String cause;

    /**
     * 备注
     */
    private String comments;

    /**
     * 异常次数(次/天)
     */
    private Integer abnormalNumber;

    /**
     * 巡检类型
     */
    private String inspectionTypes;

    /**
     * 巡检结果时间
     */
    private Date createTime;

    /**
     * 异常明细
     */
    private List<DeviceAbnormalLogVO> deviceAbnormalLogVO;

}
