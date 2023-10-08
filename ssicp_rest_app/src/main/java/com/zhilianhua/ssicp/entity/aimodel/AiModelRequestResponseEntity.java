package com.zhilianhua.ssicp.entity.aimodel;

import com.zhilianhua.ssicp.entity.aimodel.enums.MediumTypeEnum;
import com.zhilianhua.ssicp.entity.aimodel.enums.NodeTypeEnum;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class AiModelRequestResponseEntity {
    //模型id
    private int iModelID = 1;
    //节点结构体数组
    private List<STNodeEntity> lstNode = Collections.singletonList(new STNodeEntity());
    //管线结构体数组
    private List<STPipeEntity> lstPipe = Collections.singletonList(new STPipeEntity());
    //设备结构体数组，暂时不用你不用管
    private List lstDevice = Collections.emptyList();
    //环境结构体
    private EnvironmentEntity stEnvi = new EnvironmentEntity();
    //1代表蒸汽
    private MediumTypeEnum m_MType = MediumTypeEnum.GAS;
    //管线根数
    private int iPipeCount;
    //节点个数
    private int iNodeCount;
    //设备个数默认0
    private int iDeviceCount;
    //气源个数
    private int iInNodeCount;
    //基础温度，用于热量计算默认 150
    private double dBaseTemp = 150;
    //基础压力，用于压力计算 默认1000
    private double dBasePressure = 1000;
    //默认true
    private boolean bCal = true;
    //是否有环路  默认false
    private boolean bhuanlu = false;

    public int getiPipeCount() {
        return lstPipe.size();
    }

    public int getiNodeCount() {
        return lstNode.size();
    }

    public int getiDeviceCount() {
        return iDeviceCount;
    }

    public int getiInNodeCount() {
        return (int) lstNode.stream().filter(stNodeEntity -> stNodeEntity.getINodeType() == NodeTypeEnum.GAS).count();
    }
}
