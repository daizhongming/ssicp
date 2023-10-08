package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.PipelineAnalyse;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 管网分析表
 * @author 代忠明
 * @date 2023-06-29
 */
public interface PipelineAnalyseMapper {

    PageResultEntity<PipelineAnalyse> search(PipelinePageReqSearchParams pipelinePageReqSearchParams);

}