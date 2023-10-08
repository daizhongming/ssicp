package com.zhilianhua.ssicp.entity.request;

import com.zhilianhua.ssicp.entity.db.Pipeline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipelineDTO {
    private Pipeline pipeline;

    public static PipelineDTO EMPTY = new PipelineDTO(null);
    public static PipelineDTO BUILDER = new PipelineDTO();

    public PipelineDTO pipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }
}
