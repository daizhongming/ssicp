package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 智能运营分析
 * @author 代忠明
 * @date 2023-07-03
 */
@Data
public class OperationalAnalytics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private Long id;

    /**
    * 管线id
    */
    private String pipelineId;

    /**
    * 编号
    */
    private String code;

    /**
    * 统计类型
    */
    private String type;

    /**
    * 名称
    */
    private String name;

    /**
    * 流量（t/h）
    */
    private Double flowRate;

    /**
    * 表压（mpa）
    */
    private Double gaugePressure;

    /**
    * 温度（℃）
    */
    private Double temperature;

    /**
    * 比焓（kj/kg）
    */
    private Double specificEnthalpy;

    /**
    * 热量（gj/h）
    */
    private Double heat;

    /**
    * 备注
    */
    private String remark;

    public OperationalAnalytics() {
    }

}