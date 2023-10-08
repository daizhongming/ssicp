package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.DeviceAlarm;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmDeviceVO;
import com.zhilianhua.ssicp.entity.response.DeviceAlarmVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @description 设备指标信息表
 * @author 代忠明
 * @date 2023-07-06
 */
public interface DeviceAlarmMapper {

    List<DeviceAlarm> findAll();

    DeviceAlarmVo getById(String id);

    void saveBatch(List<DeviceAlarm> deviceAlarms);

    List<DeviceAlarm> selectBydid(@Param("did") String did);

    List<DeviceAlarm> getIndicatortemplate(List<String> deviceAlarmIds);

    void update(DeviceAlarm deviceAlarm);

    void insert(DeviceAlarm deviceAlarm);

    DeviceAlarm findAlarBydidAndindex(@Param("index") String index, @Param("did") String did);

    void deleteBydId(@Param("deviceId") String deviceId);

    void ignore(@Param("deviceId")String deviceId, @Param("indexId")String indexId, @Param("ignoreTime")Date ignoreTime);
}