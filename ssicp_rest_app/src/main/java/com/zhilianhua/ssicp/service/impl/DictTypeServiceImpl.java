package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.db.DictType;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.DictVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.DictTypeMapper;
import com.zhilianhua.ssicp.service.DictDataService;
import com.zhilianhua.ssicp.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 代忠明
 * @description 字典类型表
 * @date 2023-06-27
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Override
    public void insert(DictType dictType) {
        dictTypeMapper.insert(dictType);
    }

    @Override
    public void delete(String code) {
        dictTypeMapper.delete(code);
    }

    @Override
    public void update(DictType dictType) {
        dictTypeMapper.update(dictType);
    }

    @Override
    public DictType load(String code) {
        DictType dictType = dictTypeMapper.load(code);
        return dictType;
    }

    @Override
    public PageResultEntity<DictType> pageList(DictTypeParams dictTypeParams) {
        //2.检查分页参数
        return dictTypeMapper.pageList(dictTypeParams);
    }

    @Autowired
    private DictDataService dictDataService;

    @Override
    public List<DictVo> findAll() {
        List<DictData> dictDataList = dictDataService.findAll();
        return dictTypeMapper.findAll().stream().map(dictType -> {
            DictVo dictVo = new DictVo();
            dictVo.setCode(dictType.getCode());
            dictVo.setDictName(dictType.getDictName());
            List<DictData> dataList = dictDataList.stream().filter(dictData -> StringUtils.equals(dictData.getTypeCode(), dictType.getCode())).collect(Collectors.toList());
            dictVo.setDictDataList(dataList);
            return dictVo;
        }).collect(Collectors.toList());
    }
}