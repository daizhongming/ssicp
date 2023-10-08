package com.zhilianhua.ssicp.remote;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zhilianhua.ssicp.async.DeviceIndexLogAsyncHandler;
import com.zhilianhua.ssicp.entity.aimodel.AiModelRequestResponseEntity;
import com.zhilianhua.ssicp.entity.aimodel.STNodeEntity;
import com.zhilianhua.ssicp.entity.aimodel.STPipeEntity;
import com.zhilianhua.ssicp.entity.aimodel.enums.NodeTypeEnum;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.response.DeviceDataVo;
import com.zhilianhua.ssicp.service.ConfigService;
import com.zhilianhua.ssicp.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AiModelService {
    @Autowired
    private ConfigService configService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceIndexLogAsyncHandler deviceIndexLogAsyncHandler;

    public String call(String params) {
        return new RestTemplate().postForObject(configService.getAiModelServiceUrl(), params, String.class);
    }

    public void calc() {
        // 拿到所有设备数据
        Map<String, DeviceDataVo> allDeviceData = deviceService.findAllDeviceData();
        // 模型id:管段对象
        Map<Integer, STPipeEntity> pipeMap = new HashMap<>();
        // 模型id:节点对象
        Map<Integer, STNodeEntity> nodeMap = new HashMap<>();
        // 模型id和设备id映射关系
        AtomicInteger i = new AtomicInteger(1);
        Map<Integer, String> model2Device = new HashMap();
        Map<String, Integer> device2Model = new HashMap();
        // 关系 {设备id,管线id}
        List<String[]> relations = new ArrayList();
        allDeviceData.entrySet().forEach(entry -> {
            DeviceDataVo deviceDataVo = entry.getValue();
            String deviceId = entry.getKey();
            int modelId = i.getAndIncrement();
            model2Device.put(modelId, deviceId);
            device2Model.put(deviceId, modelId);
            Optional<DeviceTypeEnum> deviceTypeEnumOptional = DeviceTypeEnum.getByParentDeviceType(deviceDataVo.getDevice().getParentDeviceType());
            if (deviceTypeEnumOptional.isPresent()) {
                switch (deviceTypeEnumOptional.get()) {
                    case PIPE: {
                        STPipeEntity stPipeEntity = new STPipeEntity();
                        stPipeEntity.setIPipeID(modelId);
                        pipeMap.put(modelId, stPipeEntity);
                        break;
                    }
                    case VALVE: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.VALVE);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pipeExit", "pipeInlet");
                        nodeMap.put(modelId, stNodeEntity);
                        break;
                    }
                    case INSTRUMENT: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.POINT);
                        nodeMap.put(modelId, stNodeEntity);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pipeExit", "pipeInlet");
                        break;
                    }
                    case PRODUCTION: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.GAS);
                        nodeMap.put(modelId, stNodeEntity);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pid");
                        break;
                    }
                    case CONSUMPTION: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.USER);
                        nodeMap.put(modelId, stNodeEntity);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pid");
                        break;
                    }
                    case COMPENSATOR: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.COMPENSATOR);
                        nodeMap.put(modelId, stNodeEntity);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pipeExit", "pipeInlet");
                        break;
                    }
                    case TEE: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.SANTONG);
                        nodeMap.put(modelId, stNodeEntity);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pipeExit", "pipeInlet");
                        break;
                    }
                    case ELBOW: {
                        STNodeEntity stNodeEntity = new STNodeEntity();
                        stNodeEntity.setINodeID(modelId);
                        stNodeEntity.setINodeType(NodeTypeEnum.ELBOW);
                        nodeMap.put(modelId, stNodeEntity);
                        addRelation(relations, deviceDataVo, false, allDeviceData, "pipeExit", "pipeInlet");
                        break;
                    }
                }
            }
        });
        //补全管段的关系
        pipeMap.entrySet().forEach(entry -> {
            int pipeModelId = entry.getKey();
            String pipeId = model2Device.get(pipeModelId);
            List<Integer> nodeIds = relations.stream().filter(strings -> StringUtils.equals(strings[1], pipeId)).map(strings -> strings[0]).map(device2Model::get).collect(Collectors.toList());
            STPipeEntity stPipeEntity = entry.getValue();
            // 通过模型id拿到管段id
            stPipeEntity.setIOutNode(CollectionUtils.isNotEmpty(nodeIds) ? nodeIds.get(0) : -9999);
            stPipeEntity.setIInNode(CollectionUtils.isNotEmpty(nodeIds) && nodeIds.size() >= 2 ? nodeIds.get(1) : -9999);
        });
        //补全设备的关系
        nodeMap.entrySet().forEach(entry -> {
            int nodeModelId = entry.getKey();
            String nodeDeviceId = model2Device.get(nodeModelId);
            List<Integer> pipeIds = relations.stream().filter(strings -> StringUtils.equals(strings[0], nodeDeviceId)).map(strings -> strings[1]).map(device2Model::get).collect(Collectors.toList());
            STNodeEntity stNodeEntity = entry.getValue();
            stNodeEntity.setIPipe1(CollectionUtils.isNotEmpty(pipeIds) ? pipeIds.get(0) : -9999);
            stNodeEntity.setIPipe2(CollectionUtils.isNotEmpty(pipeIds) && pipeIds.size() >= 2 ? pipeIds.get(1) : -9999);
            stNodeEntity.setIPipe3(CollectionUtils.isNotEmpty(pipeIds) && pipeIds.size() >= 3 ? pipeIds.get(2) : -9999);
        });

        // 将设备数据转成模型请求
        AiModelRequestResponseEntity aiModelRequestResponseEntity = new AiModelRequestResponseEntity();
        aiModelRequestResponseEntity.setLstNode(nodeMap.values().stream().collect(Collectors.toList()));
        aiModelRequestResponseEntity.setLstPipe(pipeMap.values().stream().collect(Collectors.toList()));

        // 调用模型请求并获取响应结果
        String request = JSON.toJSONString(aiModelRequestResponseEntity);
        log.info("aimodel request:{}", request);
        String response = call(request);
        log.info("aimodel response:{}", response);
        aiModelRequestResponseEntity = JSON.parseObject(response, AiModelRequestResponseEntity.class);

        // 将响应结果数据写入到缓存中
        Instant time = new Date().toInstant();
        aiModelRequestResponseEntity.getLstPipe().forEach(stPipeEntity -> {
            String pipeId = model2Device.get(stPipeEntity.getIPipeID());
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "压降", "kpa/km", stPipeEntity.getDPdrop(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "温降", "℃/km", stPipeEntity.getDTdrop(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "流速", "m/s", stPipeEntity.getDVelocity(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "表面温度", "℃", stPipeEntity.getDSurfaceT(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "保温效率", "%", stPipeEntity.getDDiatherEff(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "实际热流", "w/m^2", stPipeEntity.getDCHeat(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "理论热流", "w/m^2", stPipeEntity.getDDHeat(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "合格热流", "w/m^2", stPipeEntity.getDGHeat(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "冷凝水量", "kg/h", stPipeEntity.getDCondensate(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(pipeId, DeviceTypeEnum.PIPE.getCode(), "产生位置", "m", stPipeEntity.getDConPosition(), time));
        });
        aiModelRequestResponseEntity.getLstNode().forEach(stNodeEntity -> {
            String nodeId = model2Device.get(stNodeEntity.getINodeID());
            DeviceTypeEnum deviceTypeEnum = DeviceTypeEnum.getByParentDeviceType(stNodeEntity.getINodeType().getCode() + "").get();
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(nodeId, deviceTypeEnum.getCode(), "计算温度", "℃", stNodeEntity.getDCTemperature(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(nodeId, deviceTypeEnum.getCode(), "计算压力", "MPa", stNodeEntity.getDCPressure(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(nodeId, deviceTypeEnum.getCode(), "计算流量", "t/h", stNodeEntity.getDCFlux(), time));
            deviceIndexLogAsyncHandler.handle(new DeviceIndexLog(nodeId, deviceTypeEnum.getCode(), "焓", "kj/kg", stNodeEntity.getDEnthalpy(), time));
        });

    }

    private void addRelation(List<String[]> relations, DeviceDataVo deviceDataVo, boolean isArray, Map<String, DeviceDataVo> allDeviceData, String... fields) {
        String deviceId = deviceDataVo.getDevice().getId();
        JSONObject jsonObject = (JSONObject) deviceDataVo.getExtra();
        for (String field : fields) {
            String fieldString = jsonObject.getString(field);
            if (StringUtils.isNotBlank(fieldString)) {
                List<String> pipeIds = isArray ? JSON.parseArray(fieldString, String.class) : Collections.singletonList(fieldString);
                for (String pipeId : pipeIds) {
                    if (StringUtils.isNoneBlank(pipeId) && allDeviceData.containsKey(pipeId)) {
                        relations.add(new String[]{deviceId, pipeId});
                    }
                }
            }
        }

    }
}
