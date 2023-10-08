package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.Pipeline;
import com.zhilianhua.ssicp.entity.request.PipelineDTO;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.PipelineVo;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.PipelineMapper;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管线表 服务实现类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@Service
@Slf4j
public class PipelineServiceImpl implements PipelineService {
    @Autowired
    private PipelineMapper pipelineMapper;

    @Override
    public Pipeline getid(String pipelineId) {
        return pipelineMapper.getid(pipelineId);
    }

    @Override
    public void insert(Pipeline pipeline) {
        pipeline.setId(IdUtils.uuid());
        pipelineMapper.insert(pipeline);
    }

    @Override
    public void del(String id) {
        //逻辑删除管线
        pipelineMapper.updateDeleteFlag(id);
    }

    @Override
    public void update(Pipeline pipeline) {
        pipelineMapper.update(pipeline);
    }

    @Override
    public PipelineDTO load(String id) {
        PipelineDTO pipelineDTO = new PipelineDTO();
        Pipeline pipeline = pipelineMapper.selectById(id);
        pipelineDTO.setPipeline(pipeline);
        return PipelineDTO.BUILDER
                .pipeline(pipeline);
    }

    @Override
    public List<PipelineVo> pressurePipelineGrouping(List<String> regions) {
        return pipelineMapper.pressurePipelineGrouping(regions);
    }

    @Override
    public PageResultEntity<Pipeline> pageList(PipelinePageReqSearchParams pageReqSearchParams) {
        PageResultEntity<Pipeline> pipelinePageResultEntity = pipelineMapper.pageList(pageReqSearchParams);
        return pipelinePageResultEntity;
    }

    @Override
    public List<Pipeline> getBypid(String id) {
        return pipelineMapper.getBypid(id);
    }

    @Override
    public List<Pipeline> findAll() {
        return pipelineMapper.findAll();
    }
}
