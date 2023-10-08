package com.zhilianhua.ssicp.entity.request;

import com.zhilianhua.ssicp.entity.db.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class DeviceDTO {
    //    设备
    @Valid
    private Device device;

    //    仪表
    @Valid
    private DeviceInstrument deviceInstrument;

    //    阀门
    @Valid
    private DeviceValve deviceValve;

    //    补偿器
    @Valid
    private DeviceCompensator deviceCompensator;

    //     疏水阀
    @Valid
    private DeviceSteamTraps deviceSteamTraps;

    //     弯头/三通
    @Valid
    private DeviceElbowTree deviceElbowTree;

    //    产用汽点
    @Valid
    private DeviceGasPoint deviceGasPoint;

    //    指标集合
    @Valid
    private List<DeviceAlarm> deviceAlarmList;

    @Valid
    private PipeAdd pipeAdd;

    private Map<String, Object> realTimeData;

    public DeviceDTO(Device device) {
        this.device = device;
    }
}
