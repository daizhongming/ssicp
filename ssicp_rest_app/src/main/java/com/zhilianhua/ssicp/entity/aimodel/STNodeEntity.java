package com.zhilianhua.ssicp.entity.aimodel;

import com.zhilianhua.ssicp.entity.aimodel.enums.NodeTypeEnum;
import lombok.Data;

@Data
public class STNodeEntity {
    //节点id
    private int iNodeID = 1;
    //节点类型
    private NodeTypeEnum iNodeType = NodeTypeEnum.GAS;
    //计算准备温度  //你要给我的温度
    private double dMTemperature = 100;
    //计算准备压力//你要给我的压力  单位kpa界面显示可能会是MPA要乘1000
    private double dMPressure = 1000;
    //计算准备流量 //你要给我的流量
    private double dMFlux = 20;
    //节点关联的管线ID1
    private int iPipe1 = 5;
    //节点关联的管线ID2
    private int iPipe2 = -9999;
    //节点关联的管线ID3
    private int iPipe3 = -9999;
    ////设备编号这个ID给0或者不给
    private int iDevice = -9999;
    //计算温度   //计算完返回的温度
    private double dCTemperature = -9999;
    //计算压力 //计算完返回的压力
    private double dCPressure = -9999;
    //计算流量  //计算完返回的流量
    private double dCFlux = -9999;
    //焓
    private double dEnthalpy = -9999;
    //熵
    private double dEntropy = -9999;
    //密度
    private double dDensity = -9999;
    //饱和压力
    private double dSaturationPressure = -9999;
    //饱和温度
    private double dSaturationTemperature = -9999;
    //含水百分比
    private double dCondensatebaifenbi = -9999;
    //相对位置
    private double dLocal = -9999;
    //粘度
    private double dViscosity = -9999;

    private boolean bVisited = false;
    private boolean bUsing = false;
    ////默认为true,如果为false，则不计算
    private boolean bCal = true;
    //是否已知
    private boolean bKnown = false;
}
