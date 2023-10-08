package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.DictType;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.DictVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

/**
 * @author 代忠明
 * @description 字典类型表
 * @date 2023-06-27
 */
public interface DictTypeService {

    void insert(DictType dictType);

    void delete(String code);

    void update(DictType dictType);

    DictType load(String code);

    PageResultEntity<DictType> pageList(DictTypeParams dictTypeParams);

    List<DictVo> findAll();
}