package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.zhilianhua.ssicp.entity.db.Device;
import com.zhilianhua.ssicp.entity.db.KnowledgeBase;
import com.zhilianhua.ssicp.entity.db.KnowledgeBaseData;
import com.zhilianhua.ssicp.entity.db.KonwledgeBusiness;
import com.zhilianhua.ssicp.entity.request.KnowledgeBaseDTO;
import com.zhilianhua.ssicp.entity.request.searchparams.KnowledgeBaseParams;
import com.zhilianhua.ssicp.entity.response.KnowledgeBaseVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.KnowledgeBaseMapper;
import com.zhilianhua.ssicp.service.DeviceService;
import com.zhilianhua.ssicp.service.KnowledgeBaseDataService;
import com.zhilianhua.ssicp.service.KnowledgeBaseService;
import com.zhilianhua.ssicp.service.PipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 知识库
 * @date 2023-08-18
 */
@Service
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {
    @Autowired
    private KnowledgeBaseMapper knowledgeBaseMapper;

    @Autowired
    private PipeService pipeService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private KnowledgeBaseDataService knowledgeBaseDataService;

    @Override
    public PageResultEntity<KnowledgeBaseVO> findPage(KnowledgeBaseParams knowledgeBaseParams) {
        return knowledgeBaseMapper.findPage(knowledgeBaseParams);
    }

    @Override
    public Map<String, Object> getdevice() {
        HashMap<String, Object> map = new HashMap<>();
        //查询管段
        List<Map<String, String>> pipes = pipeService.selectList();
        map.put("pipe", pipes);
        //查询产气点
        List<Map<String, String>> chan = deviceService.selectList(Device.CHANQIDIAN);
        map.put("steam_production_point", chan);

        //查询用气点
        List<Map<String, String>> yong = deviceService.selectList(Device.YONGQIDIAN);
        map.put("with_steam_spots", yong);

        //查询阀门
        List<Map<String, String>> famen = deviceService.selectList(Device.FAMEN);
        map.put("valve", famen);

        //查询仪表
        List<Map<String, String>> yibiao = deviceService.selectList(Device.YIBIAO);
        map.put("instrument", yibiao);

        //查询疏水阀
        List<Map<String, String>> shushuifa = deviceService.selectList(Device.SHUSHUIFA);
        map.put("steam_traps", shushuifa);

        //查询补偿器
        List<Map<String, String>> buchangqi = deviceService.selectList(Device.BUCHANGQI);
        map.put("compensator", buchangqi);

        //查询三通
        List<Map<String, String>> santong = deviceService.selectList(Device.SANTONG);
        map.put("tee", santong);

        //查询弯头
        List<Map<String, String>> wantou = deviceService.selectList(Device.WANTOU);
        map.put("elbow", wantou);

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(KnowledgeBaseDTO knowledgeBaseDTO) {
        //知识库基本数据
        KnowledgeBase knowledgeBase = knowledgeBaseDTO.getKnowledgeBase();
        //知识库明细
        Map<String, List<KnowledgeBaseData>> indexs = knowledgeBaseDTO.getIndexs();
        for (Map.Entry<String, List<String>> stringListEntry : knowledgeBaseDTO.getDeviceType().entrySet()) {
            String key = stringListEntry.getKey();
            List<String> value = stringListEntry.getValue();
            if (value.size() != 0) {
                List<KnowledgeBaseData> knowledgeBaseData = indexs.get(key);
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setId(null));
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setBaseId(knowledgeBase.getId()));
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setDeviceIds(JSONArray.toJSONString(value)));
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setDeviceType(key));
                knowledgeBaseDataService.saveBatch(knowledgeBaseData);
            }
        }
        knowledgeBaseMapper.insert(knowledgeBase);
    }

    @Override
    public KnowledgeBaseDTO echo(String id) {
        KnowledgeBaseDTO knowledgeBaseDTO = new KnowledgeBaseDTO();
        //知识库整合
        KnowledgeBase knowledgeBase = knowledgeBaseMapper.selectById(id);
        knowledgeBaseDTO.setKnowledgeBase(knowledgeBase);

        //查询知识库明细
        List<KnowledgeBaseData> knowledgeBaseDataList = knowledgeBaseDataService.selectBykid(id);

        //设备类型整合
        Map<String, List<String>> stringListHashMap = new HashMap<>();
        for (KnowledgeBaseData knowledgeBaseData : knowledgeBaseDataList) {
            stringListHashMap.put(knowledgeBaseData.getDeviceType(), JSONArray.parseArray(knowledgeBaseData.getDeviceIds(), String.class));
        }
        knowledgeBaseDTO.setDeviceType(stringListHashMap);

        //设备指标整合
        knowledgeBaseDTO.setIndexs(knowledgeBaseDataList.stream().collect(Collectors.groupingBy(KnowledgeBaseData::getDeviceType)));
        return knowledgeBaseDTO;
    }

    @Override
    public List<KonwledgeBusiness> getPushRecords(String id) {
        List<KonwledgeBusiness> konwledgeBusinesses = knowledgeBaseMapper.getPushRecords(id);
        return konwledgeBusinesses;
    }

    @Override
    public KnowledgeBase getById(String id) {
        return knowledgeBaseMapper.getById(id);
    }

    @Override
    public void readIncreasing(String id) {
        KnowledgeBase knowledgeBase = knowledgeBaseMapper.getById(id);
        knowledgeBase.setReadCount(knowledgeBase.getReadCount()+1);
        knowledgeBaseMapper.update(knowledgeBase);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(KnowledgeBaseDTO knowledgeBaseDTO) {
        //知识库基本数据
        KnowledgeBase knowledgeBase = knowledgeBaseDTO.getKnowledgeBase();

        //删除明细
        knowledgeBaseDataService.deleteByBaseId(knowledgeBase.getId());

        //知识库明细新增
        Map<String, List<KnowledgeBaseData>> indexs = knowledgeBaseDTO.getIndexs();
        for (Map.Entry<String, List<String>> stringListEntry : knowledgeBaseDTO.getDeviceType().entrySet()) {
            String key = stringListEntry.getKey();
            List<String> value = stringListEntry.getValue();
            if (value.size() != 0) {
                List<KnowledgeBaseData> knowledgeBaseData = indexs.get(key);
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setId(null));
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setBaseId(knowledgeBase.getId()));
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setDeviceIds(JSONArray.toJSONString(value)));
                knowledgeBaseData.forEach(knowledgeBaseData1 -> knowledgeBaseData1.setDeviceType(key));
                knowledgeBaseDataService.saveBatch(knowledgeBaseData);
            }
        }
        knowledgeBaseMapper.update(knowledgeBase);

    }

    @Override
    public void delete(String id) {

        KnowledgeBase knowledgeBase = new KnowledgeBase();
        knowledgeBase.setId(id);
        knowledgeBase.setDeleteFlag(1);
        knowledgeBaseMapper.update(knowledgeBase);
    }
}