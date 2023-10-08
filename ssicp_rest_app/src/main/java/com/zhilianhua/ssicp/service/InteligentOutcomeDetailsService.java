package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.InteligentOutcomeDetails;
import com.zhilianhua.ssicp.entity.response.IntelligentOutcomeDetailsVo;

import java.util.List;

public interface InteligentOutcomeDetailsService {

    void insert(InteligentOutcomeDetails inteligentOutcomeDetails);

    void update(InteligentOutcomeDetails inteligentOutcomeDetails);

    List<IntelligentOutcomeDetailsVo> selectByoutcomeCode(String outcomeCode);
}