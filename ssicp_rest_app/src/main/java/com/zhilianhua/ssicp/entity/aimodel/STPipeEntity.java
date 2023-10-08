package com.zhilianhua.ssicp.entity.aimodel;

import com.alibaba.fastjson2.annotation.JSONField;
import com.zhilianhua.ssicp.entity.aimodel.enums.STInsulParaEnum;
import lombok.Data;

@Data
public class STPipeEntity {
    //管段id
    private int iPipeID = 5;
    //管件内径（米）
    private double dPipeInD = 0.8;
    //保温层导热系数
    @JSONField(serializeUsing = STInsulParaEnum.STInsulParaEnumWriter.class, deserializeUsing = STInsulParaEnum.STInsulParaEnumReader.class)
    private STInsulParaEnum stPara1 = STInsulParaEnum.岩棉制品;
    @JSONField(serializeUsing = STInsulParaEnum.STInsulParaEnumWriter.class, deserializeUsing = STInsulParaEnum.STInsulParaEnumReader.class)
    private STInsulParaEnum stPara2 = STInsulParaEnum.岩棉制品;
    @JSONField(serializeUsing = STInsulParaEnum.STInsulParaEnumWriter.class, deserializeUsing = STInsulParaEnum.STInsulParaEnumReader.class)
    private STInsulParaEnum stPara3 = STInsulParaEnum.岩棉制品;
    // 入口节点ID
    private int iInNode = 1;
    // 出口节点ID
    private int iOutNode = 2;
    // 结构参数
    private STStructureEntity stStructure = new STStructureEntity();
    // 管件参数
    private STFittingsEntity stFittings = new STFittingsEntity();
    //测量流量（t/h）
    private double dMFlux = -9999;
    //计算流量（t/h）
    private double dCFlux = -9999;
    //疏水带汽比
    private double dGWRatio = -9999;
    //疏水效率
    private double dWEff = -9999;
    //含水百分比
    private double dCondensatebaifenbi = -9999;
    //"停用/启用
    ////估计以后将不用"
    private boolean bUsing = true;
    private boolean bVisited = false;
    //是否计算此管线
    private boolean bCal = true;
    //"是否已知
    //用于提前计算模型 "
    private boolean bKnown = false;
    //测量值是否是出口流量
    private boolean bOutFlux = false;
    //压降
    private double dPdrop = -9999;
    //流速
    private double dVelocity = -9999;
    //温降
    private double dTdrop = -9999;
    //附加压降余量
    private double dAddP = -9999;
    //入口温度
    private double dInTemp = -9999;
    //出口温度
    private double dOutTemp = -9999;
    //是否已经计算出口温度
    private boolean bCalTemp = false;
    //散热量
    private double dHeatloss = -9999;
    //冷凝水量
    private double dCondensate = -9999;
    //产生冷凝水位置
    private double dConPosition = -9999;
    //保温层传热系数
    private double dKheat = -9999;
    //平均密度
    private double dMdensity = -9999;
    //平均粘度
    private double dMviscosity = -9999;
    //保温层外表面温度
    private double dSurfaceT = -9999;
    //保温效率
    private double dDiatherEff = -9999;
    //实际热流
    private double dCHeat = -9999;
    //设计热流
    private double dDHeat = -9999;
    //合格热流
    private double dGHeat = -9999;
    //冷凝水热量
    private double dConEnergy = -9999;
    //是否含有冷凝水
    private boolean bcondensa = false;
    //是否在环路上
    private boolean binhuanlu = false;
    //压降系数
    private double dC = -9999;
}
