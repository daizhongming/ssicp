package com.zhilianhua.ssicp.entity.request;

import com.zhilianhua.ssicp.entity.db.KnowledgeBase;
import com.zhilianhua.ssicp.entity.db.KnowledgeBaseData;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class KnowledgeBaseDTO {
    private KnowledgeBase knowledgeBase;

    private Map<String, List<String>> deviceType;

    private Map<String,List<KnowledgeBaseData>> indexs;
}