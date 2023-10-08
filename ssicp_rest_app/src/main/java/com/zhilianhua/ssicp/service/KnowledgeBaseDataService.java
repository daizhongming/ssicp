package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.KnowledgeBaseData;

import java.util.List;

/**
 * @description 知识明细
 * @date 2023-08-21
 */
public interface KnowledgeBaseDataService {

    void saveBatch(List<KnowledgeBaseData> knowledgeBaseDataList);

    List<KnowledgeBaseData> selectBykid(String kid);

    void deleteByBaseId(String baseId);
}