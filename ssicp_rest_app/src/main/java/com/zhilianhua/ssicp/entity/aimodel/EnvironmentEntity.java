package com.zhilianhua.ssicp.entity.aimodel;

import com.zhilianhua.ssicp.entity.aimodel.enums.SoilConditionEnum;
import com.zhilianhua.ssicp.entity.aimodel.enums.WeatherEnum;
import lombok.Data;

@Data
public class EnvironmentEntity {
    //大气压力 默认101kpa
    private double dAtmosphere = 101;
    //环境温度  默认25
    private double dTemperature = 25;
    //土壤温度 默认15
    private double dSoilTemperature = 15;
    //土壤状况
    private SoilConditionEnum iSoilCondition = SoilConditionEnum.干土壤;
    //天气状况，分晴天、大雨、小雨中雨
    private WeatherEnum iWeather = WeatherEnum.晴天;
    //风级数，默认2级风一共到12级
    private int iWindPower = 2;
}
