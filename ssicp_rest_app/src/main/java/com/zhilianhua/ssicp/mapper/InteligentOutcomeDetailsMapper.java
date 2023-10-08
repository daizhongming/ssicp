package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.InteligentOutcomeDetails;
import com.zhilianhua.ssicp.entity.response.IntelligentOutcomeDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InteligentOutcomeDetailsMapper {

    void insert(InteligentOutcomeDetails inteligentOutcomeDetails);

    List<IntelligentOutcomeDetailsVo> selectByoutcomeCode(@Param("outcomeCode") String outcomeCode);

    void update(InteligentOutcomeDetails inteligentOutcomeDetails);
}