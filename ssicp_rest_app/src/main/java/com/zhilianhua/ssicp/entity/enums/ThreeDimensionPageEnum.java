package com.zhilianhua.ssicp.entity.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum ThreeDimensionPageEnum {
    OVERVIEW("overview", "态势感知-管网总览"),
    TEMPERATURE_DROP("temperatureDrop", "态势感知-温降分布"),
    PRESSURE_DROP("pressureDrop", "态势感知-压降分布"),
    FLOW_VELOCITY("flowVelocity", "态势感知-流速分布"),
    CONDENSATE("condensate", "态势感知-冷凝水分布"),
    MONITOR("monitor", "全时监测"),
    SIMULATION("simulation", "仿真预测");

    private String code;
    private String name;


    ThreeDimensionPageEnum(String code, String name) {
        this.code = code;
        this.name = name;

    }

    public static Optional<ThreeDimensionPageEnum> getByPageCode(String pageCode) {
        if (StringUtils.isBlank(pageCode)) return Optional.empty();
        return Arrays.stream(ThreeDimensionPageEnum.values()).filter(pageEnum -> StringUtils.equals(pageEnum.code, pageCode)).findFirst();
    }
}
