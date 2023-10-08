package com.zhilianhua.ssicp.entity.request;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.db.Pipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PipeAdd {
    //管段表
    @NotNull(message = "管段信息不能为空")
    @Valid
    private Pipe pipe;
    //管段维护信息
    private PipeMaintenance pipeMaintenance;
    //管段设计信息
    private PipeDevise pipeDevise;
    //管段保温信息
    @NotNull(message = "管段保温信息不能为空")
    @Valid
    private PipeInsulation pipeInsulation;
    private List<DeviceAlarm> deviceAlarmList;
}