package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.PipelineAnalyse;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.PipelineAnalyseMapper;
import com.zhilianhua.ssicp.service.PipelineAnalyseService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @description 管网分析表
 * @author 代忠明
 * @date 2023-06-29
 */
@Service
public class PipelineAnalyseServiceImpl implements PipelineAnalyseService {
    @Autowired
    PipelineAnalyseMapper pipelineAnalyseMapper;

    @Override
    public ResponseEntity pageList(PipelinePageReqSearchParams pipelinePageReqSearchParams) {
        //2.检查分页参数
//        pipelinePageReqSearchParams.checkParam();
        PageResultEntity<PipelineAnalyse> pageList = pipelineAnalyseMapper.search(pipelinePageReqSearchParams);
        return ResponseEntityUtils.success(pageList);
    }
}