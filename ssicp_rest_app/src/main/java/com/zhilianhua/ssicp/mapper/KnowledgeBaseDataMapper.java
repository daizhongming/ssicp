package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.KnowledgeBaseData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 知识明细
 * @date 2023-08-21
 */
public interface KnowledgeBaseDataMapper {

    void saveBatch(List<KnowledgeBaseData> knowledgeBaseDataList);

    List<KnowledgeBaseData> selectBykid(@Param("kid") String kid);

    void deleteByBaseId(String baseId);
}