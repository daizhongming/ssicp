package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PipelinePageReqSearchParams extends PageParams {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 管线id
     */
    private String pid;

    /**
     * 管线id
     */
    private List<String> pipeIds;
}
