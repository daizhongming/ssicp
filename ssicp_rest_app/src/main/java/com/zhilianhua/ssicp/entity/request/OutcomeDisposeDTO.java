package com.zhilianhua.ssicp.entity.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OutcomeDisposeDTO {
    @Valid
    @NotEmpty(message = "请填写处理信息")
    private List<OutcomeDispose> outcomeDisposes;
}
