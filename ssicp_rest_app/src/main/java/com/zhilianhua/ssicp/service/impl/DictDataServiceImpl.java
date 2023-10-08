package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.mapper.DictDataMapper;
import com.zhilianhua.ssicp.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 代忠明
 * @description 字典数据表
 * @date 2023-06-27
 */
@Service
public class DictDataServiceImpl implements DictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;

    @Override
    public void insert(DictData dictData) {
        extracted(dictData);
        dictDataMapper.insert(dictData);

    }

    private void extracted(DictData dictData) {
        int selectverify = dictDataMapper.selectverify(dictData);
        if (selectverify > 0) {
            throw new BusinessException("键值重复");
        }
    }

    @Override
    public void delete(Integer id) {
        dictDataMapper.delete(id);
    }

    @Override
    public void update(DictData dictData) {
        dictDataMapper.update(dictData);
    }

    @Override
    public DictData load(Integer id) {
        DictData dictData = dictDataMapper.load(id);
        return dictData;
    }

    @Override
    public PageResultEntity pageList(DictTypeParams dictTypeParams) {
        //2.检查分页参数
        long totalCount = dictDataMapper.pageListCount(dictTypeParams);
        List<DictData> pageList = dictDataMapper.pageList(dictTypeParams);
        return PageResultEntity.build().total(totalCount).data(pageList);
    }

    @Override
    public Map<String, List<DictData>> listAll(List<String> typecode) {
        List<DictData> dictDataList = dictDataMapper.listAll(typecode);
        Map<String, List<DictData>> typeMap = dictDataList.stream().collect(Collectors.groupingBy(DictData::getTypeCode));

//        Map<String, Map<String, String>> typeMap = dictDataList.stream().collect(Collectors.groupingBy(DictData::getTypeCode,
//                Collectors.toMap(DictData::getDictValue, DictData::getDictLabel)));
//        createGroupedTypes(typeMap, null, 0);
        return typeMap;
    }

    private static final String PRESSURE_LEVEL_TYPE_CODE = "pipeline_pressure";

    @Override
    public List<DictData> getPressureLevels() {
        return dictDataMapper.findByTypeCode(PRESSURE_LEVEL_TYPE_CODE);
    }
    @Override
    public List<DictData> findByTypeCode(String typeCode){
        return dictDataMapper.findByTypeCode(typeCode);
    }
    @Override
    public List<DictData> findAll() {
        return dictDataMapper.findAll();
    }

    @Override
    public List<DictData> findPipeIndexes() {
        return findByTypeCode(DeviceTypeEnum.PIPE.getIndexTypeCode());
    }

    @Override
    public List<DictData> findInstrumentIndexes() {
        return findByTypeCode(DeviceTypeEnum.INSTRUMENT.getIndexTypeCode());
    }

    @Override
    public List<DictData> findTrapIndexes() {
        return findByTypeCode(DeviceTypeEnum.TRAP.getIndexTypeCode());
    }

    @Override
    public List<DictData> findValveIndexes() {
        return findByTypeCode(DeviceTypeEnum.VALVE.getIndexTypeCode());
    }

    @Override
    public List<DictData> findProductionIndexes() {
        return findByTypeCode(DeviceTypeEnum.PRODUCTION.getIndexTypeCode());
    }

    @Override
    public List<DictData> findConsumptionIndexes() {
        return findByTypeCode(DeviceTypeEnum.CONSUMPTION.getIndexTypeCode());
    }

    @Override
    public List<DictData> findCompensatorIndexes() {
        return findByTypeCode(DeviceTypeEnum.COMPENSATOR.getIndexTypeCode());
    }

    private void createGroupedTypes(Map<String, List<DictData>> typeMap, Long parentId, int level) {
        List<DictData> result = new ArrayList<>();
        List<DictData> childTypes = typeMap.get(parentId);
//        if (childTypes != null) {
//            for (DictData type : childTypes) {
//                type.setDictDataList(level);
//                result.add(type);
//                List<ValveType> children = createGroupedTypes(typeMap, type.getId(), level + 1);
//                result.addAll(children);
//            }
//        }
    }
}