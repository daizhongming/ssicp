package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class DeviceGroupAlarmVO {
    private String indexname;
    private String indexid ;
    private String did;
    private String dname;
    private String parentDeviceType;

    private List<DeviceGroupAlarmVO.Class> cascadingData;

    @Data
    public static class Class {
        private String id;
        private String title;
        private List<DeviceGroupAlarmVO.Class> children;
    }
}
