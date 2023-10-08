package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.PipelineAnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代忠明
 * @description 管网分析表
 * @date 2023-06-29
 */
@RestController
@Privilege("管网分析")
@RequestMapping(value = "/pipelineAnalyse")
public class PipelineAnalyseController {
    @Autowired
    PipelineAnalyseService pipelineAnalyseService;

    /**
     * 查询 分页查询
     *
     * @author 代忠明
     * @date 2023/06/29
     **/
    @PostMapping("/pageList")
    public ResponseEntity pageList(@RequestBody PipelinePageReqSearchParams pipelinePageReqSearchParams) {
        return pipelineAnalyseService.pageList(pipelinePageReqSearchParams);
    }
}