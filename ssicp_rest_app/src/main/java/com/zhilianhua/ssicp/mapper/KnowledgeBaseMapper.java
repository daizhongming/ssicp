package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.KnowledgeBase;
import com.zhilianhua.ssicp.entity.db.KonwledgeBusiness;
import com.zhilianhua.ssicp.entity.request.searchparams.KnowledgeBaseParams;
import com.zhilianhua.ssicp.entity.response.KnowledgeBaseVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KnowledgeBaseMapper {

    PageResultEntity<KnowledgeBaseVO> findPage(KnowledgeBaseParams knowledgeBaseParams);

    void insert(KnowledgeBase knowledgeBase);

    KnowledgeBase selectById(@Param("id") String id);

    void update(KnowledgeBase knowledgeBase);

    List<KonwledgeBusiness> getPushRecords(@Param("id") String id);

    KnowledgeBase getById(@Param("id") String id);
}