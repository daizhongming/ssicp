package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class PipelineVo {
    private String pid;
    private String pname;
    private String pipeids;
    private String dictLabel;
    private List<Class> cascadingData;

    @Data
    public static class Class {
        private String id;
        private String title;
        private List<Class> children;
    }

    public void processQueryResult(List<PipelineVo> queryResult) {

    }
}
