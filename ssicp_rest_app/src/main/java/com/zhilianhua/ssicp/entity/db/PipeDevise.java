package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 管段设计信息
 * @date 2023-08-23
 */
@Data
public class PipeDevise implements Serializable {

    /**
    * 主键
    */
    private Integer id;

    /**
    * 管段id
    */
    private String pipeId;

    /**
    * 设计压力（mpa）
    */
    private Double devisePressure;

    /**
    * 设计温度（rn℃）
    */
    private Double deviseTemperature;

    /**
    * 设计流量（t/h）
    */
    private Double deviseFlux;

    /**
    * 设计寿命（年）
    */
    private Double deviseLife;

}