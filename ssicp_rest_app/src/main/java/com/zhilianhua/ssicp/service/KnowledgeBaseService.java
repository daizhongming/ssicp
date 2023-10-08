package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.KnowledgeBase;
import com.zhilianhua.ssicp.entity.db.KonwledgeBusiness;
import com.zhilianhua.ssicp.entity.request.KnowledgeBaseDTO;
import com.zhilianhua.ssicp.entity.request.searchparams.KnowledgeBaseParams;
import com.zhilianhua.ssicp.entity.response.KnowledgeBaseVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;
import java.util.Map;

/**
 * @author daizhongming
 * @description 知识库
 * @date 2023-08-18
 */
public interface KnowledgeBaseService {

    PageResultEntity<KnowledgeBaseVO> findPage(KnowledgeBaseParams knowledgeBaseParams);

    Map<String,Object> getdevice();

    void insert(KnowledgeBaseDTO knowledgeBaseDTO);

    KnowledgeBaseDTO echo(String id);

    List<KonwledgeBusiness> getPushRecords(String id);

    KnowledgeBase getById(String id);

    void readIncreasing(String id);

    void update(KnowledgeBaseDTO knowledgeBaseDTO);

    void delete(String id);
}