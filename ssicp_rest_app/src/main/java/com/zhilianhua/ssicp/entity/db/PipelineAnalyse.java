package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 管网分析表
 * @author 代忠明
 * @date 2023-06-29
 */
@Data
public class PipelineAnalyse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 管线id
     */
    private String pipelineId;

    /**
     * 管线名称
     */
    private String pipelineName;

    /**
     * 分组
     */
    private String gp;

    /**
     * 公称直径
     */
    private String nominalDiameter;

    /**
     * 长度（m）
     */
    private Double pipelineLength;

    /**
     * 流量（t/h）
     */
    private Double pipelineFlow;

    /**
     * 流速（m/s）
     */
    private Double velocityFlow;

    /**
     * 比压降（mpa/ka）
     */
    private Double pressureDrop;

    /**
     * 保温层厚度（mm）
     */
    private Double insulationThickness;

    /**
     * 保温层表面温度（15.3）
     */
    private Double insulationTemperature;

    /**
     * 保温层保温效率（%）
     */
    private Double insulationEfficiency;

    /**
     * 保温层实际热流（w/m^2）
     */
    private Double insulationActualHeatflux;

    /**
     * 保温层理论热流（w/m^2）
     */
    private Double insulationTheoreticalHeatflux;

    /**
     * 保温层合格热流（w/m^2）
     */
    private Double insulationQualifiedHeatflux;

    /**
     * 保温层保温评价
     */
    private String insulationEvaluation;

    /**
     * 保温层比温降（℃/km）
     */
    private Double insulationPressureDrop;

    /**
     * 凝结水（kg/h）
     */
    private Double condensate;

    /**
     * 凝结水产生位置（m）
     */
    private String condensateLocation;

    public PipelineAnalyse() {
    }

}