package com.zhilianhua.ssicp.task.entity;

import com.zhilianhua.ssicp.exception.BusinessException;
import com.zhilianhua.ssicp.task.check.JobParamsChecker;
import lombok.Data;

@Data
public class LogGenerationJobParams extends JobParamsChecker {
    private Double low;
    private Double senior;
    private Double seniorSenior;

    @Override
    public void check() {
        if (low == null) {
            throw BusinessException.instance("low不能为空");
        }
        if (senior == null) {
            throw BusinessException.instance("senior不能为空");
        }
        if (seniorSenior == null) {
            throw BusinessException.instance("seniorSenior不能为空");
        }
        if (low + senior + seniorSenior > 1) {
            throw BusinessException.instance("三者之和不能超过1");
        }
    }
}
