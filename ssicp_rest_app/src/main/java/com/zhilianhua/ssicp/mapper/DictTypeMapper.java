package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.DictType;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

/**
 * @description 字典类型表
 * @author 代忠明
 * @date 2023-06-27
 */
public interface DictTypeMapper {

    void insert(DictType dictType);

    void delete(String code);

    void update(DictType dictType);

    DictType load(String code);

    PageResultEntity<DictType> pageList(DictTypeParams dictTypeParams);

    List<DictType> findAll();

}