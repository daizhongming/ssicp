package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.KnowledgeBaseData;
import com.zhilianhua.ssicp.mapper.KnowledgeBaseDataMapper;
import com.zhilianhua.ssicp.service.KnowledgeBaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 知识明细
 * @date 2023-08-21
 */
@Service
public class KnowledgeBaseDataServiceImpl implements KnowledgeBaseDataService {
    @Autowired
    private KnowledgeBaseDataMapper knowledgeBaseDataMapper;

    @Override
    public void saveBatch(List<KnowledgeBaseData> knowledgeBaseDataList) {
        knowledgeBaseDataMapper.saveBatch(knowledgeBaseDataList);
    }

    @Override
    public List<KnowledgeBaseData> selectBykid(String kid) {
        return knowledgeBaseDataMapper.selectBykid(kid);
    }

    @Override
    public void deleteByBaseId(String baseId) {
        knowledgeBaseDataMapper.deleteByBaseId(baseId);
    }
}