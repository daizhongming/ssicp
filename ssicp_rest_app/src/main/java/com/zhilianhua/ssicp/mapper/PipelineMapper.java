package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.Pipeline;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.PipelineVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 管线表 Mapper 接口
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface PipelineMapper{
    void insert(Pipeline pipeline);

    Pipeline selectById(@Param("id") String id);

    void updateDeleteFlag(@Param("id") String id);

    void update(Pipeline pipeline);

    List<PipelineVo> pressurePipelineGrouping(List<String> regions);

    PageResultEntity<Pipeline> pageList(PipelinePageReqSearchParams pageReqSearchParams);

    Pipeline getid(@Param("pipelineId") String pipelineId);

    List<Pipeline> getBypid(@Param("id") String id);

    List<Pipeline> findAll();
}
