package com.zhilianhua.ssicp.entity.response;

import com.zhilianhua.ssicp.entity.db.Device;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class DeviceDataVo {
    //    设备
    private Device device;

    private Object extra;

    private Map<String, RealTimeData> realTimeData = new HashMap();

    @Data
    public static class RealTimeData {
        private String indexId;
        private Double indexValue;
        private String unit;
        private Date time;
        private Integer alarmLevel;
        private Double lowerAlarm;
        private Double seniorAlarm;
        private Double seniorSeniorAlarm;
    }
}
