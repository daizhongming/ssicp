package com.zhilianhua.ssicp.entity.aimodel;

import lombok.Data;

@Data
public class STFittingsEntity {
    //附加当量长度
    private double dAdditionalEql = 0;
    //30度单缝焊弯头
    private int iBend30 = 0;
    //45度单缝焊弯头
    private int iBend45 = 0;
    //45度单缝焊弯头
    private int iBend60 = 0;
    //45度单缝焊弯头
    private int iBend90 = 0;
    //锻压弯头R=(1.5-2)D
    private int iBend2d = 0;
    //煨弯R=4D
    private int iBend4d = 0;
    //90度方形弯头
    private int iBendsquare = 0;
    //闸阀
    private int iGatevalve = 0;
    //开度
    private double dGatevalveRatio = 0;
    //截止阀
    private int iStopvalve = 0;
    //蝶阀（全开）
    private int iButterfly = 0;
    //角阀（全开）
    private int iAngle = 0;
    //带有滤水器的底阀（全开）
    private int iBasevalve = 0;
    //旋启式止回阀
    private int iCheckvalveR = 0;
    //升降式止回阀
    private int iCheckvalveL = 0;
    //套筒补偿器（单向）
    private int iSleeveS = 0;
    //套筒补偿器（双向）
    private int iSleeveD = 0;
    //波纹补偿器（无内套）
    private int iWaveN = 0;
    //波纹补偿器（有内套）
    private int iWaveY = 0;
    //方形补偿器：三缝焊弯R=1.5D
    private int iSquare15D = 0;
    //方形补偿器：锻压弯头R=(1.5-2)D
    private int iSquare2D = 0;
    //方形补偿器：焊弯R>4D
    private int iSquare4D = 0;
    //PI型补偿器，R=1.5D
    private int iPI15D = 0;
    //PI型补偿器，R=2D
    private int iPI2D = 0;
    //除污器
    private int iClear = 0;
    //转子流量计
    private int iRotormeter = 0;
    //文丘里流量计
    private int iVenterimeter = 0;
    //孔板压降
    private double dKongban = 0;
}
