package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.InteligentOutcomeDetails;
import com.zhilianhua.ssicp.entity.response.IntelligentOutcomeDetailsVo;
import com.zhilianhua.ssicp.mapper.InteligentOutcomeDetailsMapper;
import com.zhilianhua.ssicp.service.InteligentOutcomeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteligentOutcomeDetailsServiceImpl implements InteligentOutcomeDetailsService {
    @Autowired
    private InteligentOutcomeDetailsMapper inteligentOutcomeDetailsMapper;

    @Override
    public void insert(InteligentOutcomeDetails inteligentOutcomeDetails) {
        inteligentOutcomeDetailsMapper.insert(inteligentOutcomeDetails);
    }

    @Override
    public void update(InteligentOutcomeDetails inteligentOutcomeDetails) {
        inteligentOutcomeDetailsMapper.update(inteligentOutcomeDetails);
    }

    @Override
    public List<IntelligentOutcomeDetailsVo> selectByoutcomeCode(String outcomeCode) {
        return inteligentOutcomeDetailsMapper.selectByoutcomeCode(outcomeCode);
    }
}