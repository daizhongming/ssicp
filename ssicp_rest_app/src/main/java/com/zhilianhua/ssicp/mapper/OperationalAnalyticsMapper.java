package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.OperationalAnalytics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 智能运营分析
 * @author 代忠明
 * @date 2023-07-03
 */
public interface OperationalAnalyticsMapper {

    List<OperationalAnalytics> findAll();

}