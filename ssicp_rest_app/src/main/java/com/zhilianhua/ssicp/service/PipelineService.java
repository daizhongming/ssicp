package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Pipeline;
import com.zhilianhua.ssicp.entity.request.PipelineDTO;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.PipelineVo;
import com.zhilianhua.ssicp.entity.response.TreeVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

/**
 * <p>
 * 管线表 服务类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface PipelineService {
    Pipeline getid(String pipelineId);

    void insert(Pipeline pipeline);

    void del(String id);

    void update(Pipeline pipeline);

    PipelineDTO load(String id);

    List<PipelineVo> pressurePipelineGrouping(List<String> regions);

    PageResultEntity<Pipeline> pageList(PipelinePageReqSearchParams pageReqSearchParams);

    List<Pipeline> getBypid(String id);

    List<Pipeline> findAll();
}
