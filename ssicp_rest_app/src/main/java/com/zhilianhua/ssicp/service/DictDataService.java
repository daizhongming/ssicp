package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;
import java.util.Map;

/**
 * @author 代忠明
 * @description 字典数据表
 * @date 2023-06-27
 */
public interface DictDataService {

    void insert(DictData ssicpDictType);

    void delete(Integer id);

    void update(DictData ssicpDictType);

    DictData load(Integer id);

    PageResultEntity pageList(DictTypeParams dictTypeParams);

    Map<String, List<DictData>> listAll(List<String> typecodes);

    List<DictData> getPressureLevels();

    List<DictData> findAll();

    List<DictData> findByTypeCode(String typeCode);

    List<DictData> findPipeIndexes();
    List<DictData> findInstrumentIndexes();
    List<DictData> findTrapIndexes();
    List<DictData> findValveIndexes();
    List<DictData> findProductionIndexes();
    List<DictData> findConsumptionIndexes();
    List<DictData> findCompensatorIndexes();
}