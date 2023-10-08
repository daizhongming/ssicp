//package com.zhilianhua.ssicp.service.impl;
//
//import com.zhilianhua.ssicp.entity.response.DeviceDataVo;
//import com.zhilianhua.ssicp.service.DeviceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.Objects;
//
///**
// * <p>
// * 设备表 服务实现类
// * </p>
// *
// * @author 代忠明
// * @since 2023-06-19
// */
//@Service
//public class DeviceManage {
//    @Autowired
//    private DeviceService deviceService;
//    @Autowired
//    private RedisService redisService;
//
//    public DeviceDataVo getDeviceData(String id) {
//        DeviceDataVo deviceData = deviceService.getDeviceData(id);
//        if (Objects.isNull(deviceData)) return null;
//        // 查询实时数据
//        Map<String, DeviceDataVo.RealTimeData> realTimeData = redisService.getDeviceRealTimeData(id);
//        deviceData.setRealTimeData(realTimeData);
//        return deviceData;
//    }
//}
