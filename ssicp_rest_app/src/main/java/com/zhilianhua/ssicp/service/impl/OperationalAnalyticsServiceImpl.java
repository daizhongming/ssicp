package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.OperationalAnalytics;
import com.zhilianhua.ssicp.mapper.OperationalAnalyticsMapper;
import com.zhilianhua.ssicp.service.OperationalAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description 智能运营分析
 * @author 代忠明
 * @date 2023-07-03
 */
@Service
public class OperationalAnalyticsServiceImpl implements OperationalAnalyticsService {
    @Autowired
    private OperationalAnalyticsMapper operationalAnalyticsMapper;

    @Override
    public Map findAll() {
        List<OperationalAnalytics> operationalAnalytics = operationalAnalyticsMapper.findAll();
        Map<String, List<OperationalAnalytics>> collect = operationalAnalytics.stream().collect(Collectors.groupingBy(OperationalAnalytics::getType));
        return collect;
    }
}