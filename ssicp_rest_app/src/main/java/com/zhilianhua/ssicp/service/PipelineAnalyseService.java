package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import org.springframework.http.ResponseEntity;

/**
 * @description 管网分析表
 * @author 代忠明
 * @date 2023-06-29
 */
public interface PipelineAnalyseService {

    ResponseEntity pageList(PipelinePageReqSearchParams pipelinePageReqSearchParams);
}