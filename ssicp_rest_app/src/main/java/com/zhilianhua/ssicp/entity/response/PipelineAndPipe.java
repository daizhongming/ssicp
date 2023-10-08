package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.Pipe;
import com.zhilianhua.ssicp.entity.db.Pipeline;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PipelineAndPipe {
    List<Pipeline> pipelines;
    Pipe pipes;
}
