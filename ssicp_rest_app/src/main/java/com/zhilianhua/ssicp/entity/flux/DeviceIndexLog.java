package com.zhilianhua.ssicp.entity.flux;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Measurement(name = "deviceIndexLog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceIndexLog {
    @Column(tag = true)
    private String deviceId;
    @Column(tag = true)
    private String deviceTypeCode;
    @Column(tag = true)
    private String indexId;
    @Column(tag = true)
    private String unit;
    @Column
    private Double value;
    @Column(timestamp = true)
    private Instant time;
}