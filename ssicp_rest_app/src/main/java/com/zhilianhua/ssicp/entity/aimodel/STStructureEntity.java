package com.zhilianhua.ssicp.entity.aimodel;

import com.zhilianhua.ssicp.entity.aimodel.enums.PipeTypeEnum;
import com.zhilianhua.ssicp.entity.aimodel.enums.SpanStyleEnum;
import lombok.Data;

@Data
public class STStructureEntity {
    //长度
    private double dLength = 100;
    //公称直径
    private int iDN = 1;
    //外径
    private double dDW = 0.017;
    //壁厚系列
    private int iPT = 1;
    //壁厚
    private double dPipethick = 0.035;
    //管材种类
    private PipeTypeEnum iType = PipeTypeEnum.新钢管;
    //粗糙度
    private double dRoughness = 0.0002;
    //第一层保温层厚度
    private double dInsulthick1 = 0.01;
    //第一层保温层厚度
    private double dInsulthick2 = 0;
    //第一层保温层厚度
    private double dInsulthick3 = 0;
    //敷设方式
    private SpanStyleEnum sStyle = SpanStyleEnum.室外架空;
    //埋设深度
    private double dSpandepth = -9999;
    //管沟截面积
    private double dAtube = -9999;
    //管沟周长
    private double dStube = -9999;
    //摩擦系数
    private double dFriction = 1;
    //压降因子  //默认1
    private double dFactorHyd = 1;
    //传热因子  //默认1
    private double dFactorTher = 1;
    //保温层裸露因子 //默认0
    private double dFactorExposed = 0;
    //疏水阀个数
    private int iTrapNum = 0;
    //当量长度
    private double dEql = -9999;
    //额外长度//当量长度，你不用给到时候我计算
    private double dAddEql = -9999;
}
