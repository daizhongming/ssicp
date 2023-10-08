package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 字典数据表
 * @author 代忠明
 * @date 2023-06-27
 */
@Repository
public interface DictDataMapper {

    void insert(DictData dictData);

    void delete(Integer id);

    void update(DictData dictData);

    DictData load(Integer id);

    List<DictData> pageList(DictTypeParams dictTypeParams);

    int pageListCount(DictTypeParams dictTypeParams);

    List<DictData> listAll(List<String> typecodes);

    int selectverify(DictData dictData);

    List<DictData> findByTypeCode(String typeCode);

    List<DictData> findAll();

}