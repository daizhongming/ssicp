package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.AlarmactionInfo;
import com.zhilianhua.ssicp.entity.db.DeviceAlarmLog;
import com.zhilianhua.ssicp.entity.db.IndexLog;
import com.zhilianhua.ssicp.entity.request.DeviceAlarmLogHandleRequest;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogDetailsSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.DeviceAlarmLogMapper;
import com.zhilianhua.ssicp.redis.DeviceRedisService;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DeviceAlarmLogServiceImpl implements DeviceAlarmLogService {
    @Autowired
    private DeviceAlarmLogMapper deviceAlarmLogMapper;
    @Autowired
    private DeviceAlarmService deviceAlarmService;
    @Autowired
    private AlarmactionInfoService alarmactionInfoService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private DeviceRedisService deviceRedisService;


    @Override
    public void add(DeviceAlarmLog deviceAlarmLog) {
        deviceAlarmLogMapper.add(deviceAlarmLog);
//        DeviceDataVo.RealTimeData realTimeData = new DeviceDataVo.RealTimeData();
//        realTimeData.setIndexId(deviceIndexLog.getIndexId());
//        realTimeData.setTime(Date.from(deviceIndexLog.getTime()));
//        realTimeData.setIndexValue(deviceIndexLog.getValue());
//        realTimeData.setUnit(deviceIndexLog.getUnit());
//        realTimeData.setAlarmLevel(3);
//        DeviceAlarm deviceAlarm = deviceAlarmService.findAlarBydidAndindex(deviceIndexLog.getIndexId(), deviceIndexLog.getDeviceId());
//        Device device = deviceService.load(deviceIndexLog.getDeviceId());
//        Optional<String> deviceTypeName = DeviceTypeEnum.getNameByParentDeviceType(device.getParentDeviceType());
//        // 拿到指标相关数据
//        if (Objects.nonNull(deviceAlarm) && Objects.nonNull(device) && deviceTypeName.isPresent() && deviceAlarm.isAlarm(deviceIndexLog.getValue())) {
//            // 写入报警数据
//            DeviceAlarmLog deviceAlarmLog = new DeviceAlarmLog();
//            deviceAlarmLog.setAlarmValue(deviceIndexLog.getValue());
//            deviceAlarmLog.setDeviceId(deviceIndexLog.getDeviceId());
//            deviceAlarmLog.setIndexId(deviceIndexLog.getIndexId());
//            deviceAlarmLog.setIndexName(deviceIndexLog.getIndexId());
//            deviceAlarmLog.setDeviceName(device.getDeviceName());
//            deviceAlarmLog.setDeviceTypeName(deviceTypeName.get());
//            deviceAlarmLog.setAlarmLevel(deviceAlarm.getAlarmLevel(deviceIndexLog.getValue()));
//            deviceAlarmLog.setUnit(deviceIndexLog.getUnit());
//            deviceAlarmLog.setLowerAlarm(deviceAlarm.getLowerAlarm());
//            deviceAlarmLog.setSeniorAlarm(deviceAlarm.getSeniorAlarm());
//            deviceAlarmLog.setSeniorSeniorAlarm(deviceAlarm.getSeniorSeniorAlarm());
//            deviceAlarmLog.setStartTime(Date.from(deviceIndexLog.getTime()));
//
//            messageService.addAlarm(deviceAlarmLog);
//            realTimeData.setAlarmLevel(deviceAlarm.getAlarmLevel(deviceIndexLog.getValue()));
//            realTimeData.setLowerAlarm(deviceAlarm.getLowerAlarm());
//            realTimeData.setSeniorAlarm(deviceAlarm.getSeniorAlarm());
//            realTimeData.setSeniorSeniorAlarm(deviceAlarm.getSeniorSeniorAlarm());
//        }
//        // 写入缓存
//        deviceRedisService.setDeviceRealTimeData(deviceIndexLog.getDeviceId(), deviceIndexLog.getIndexId(), realTimeData);
    }

    @Override
    public PageResultEntity<DeviceAlarmLogVo> search(DeviceAlarmLogSearchParams deviceAlarmLogSearchParams) {
        return deviceAlarmLogMapper.search(deviceAlarmLogSearchParams);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handle(DeviceAlarmLogHandleRequest deviceAlarmLogHandleRequest) {
        AlarmactionInfo alarmactionInfo = new AlarmactionInfo();
        alarmactionInfo.setId(IdUtils.uuid());
        alarmactionInfo.setResolutionReason(deviceAlarmLogHandleRequest.getAlarmReason());
        alarmactionInfo.setSummary(deviceAlarmLogHandleRequest.getHandleDescription());
        alarmactionInfo.setFileList(deviceAlarmLogHandleRequest.getFileList());
        alarmactionInfo.setFileListBefore(deviceAlarmLogHandleRequest.getFileListBefore());
        alarmactionInfo.setIgnoreTime(deviceAlarmLogHandleRequest.getIgnoreTime());
        alarmactionInfoService.add(alarmactionInfo);
        deviceAlarmLogHandleRequest.setAlarmActionInfoId(alarmactionInfo.getId());
        deviceAlarmLogMapper.handle(deviceAlarmLogHandleRequest);
        messageService.removeAlarm(deviceAlarmLogHandleRequest.getDeviceId(), deviceAlarmLogHandleRequest.getIndexId());
        // 设置报警忽略
        if (deviceAlarmLogHandleRequest.getHandleStatus().intValue() == 2 && Objects.nonNull(deviceAlarmLogHandleRequest.getIgnoreTime())) {
            deviceAlarmService.ignore(deviceAlarmLogHandleRequest.getDeviceId(), deviceAlarmLogHandleRequest.getIndexId(), deviceAlarmLogHandleRequest.getIgnoreTime());
        }
    }

    @Override
    public List<DeviceAlarmLogAlarmDetailsVo> findAlarmDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams) {
        return deviceAlarmLogMapper.findAlarmDetails(deviceAlarmLogDetailsSearchParams);
    }

    @Override
    public List<DeviceAlarmLogHandleDetailsVo> findHandleDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams) {
        return deviceAlarmLogMapper.findHandleDetails(deviceAlarmLogDetailsSearchParams);
    }

    @Override
    public List<AnalyseVo> analyseByIndexName() {
        return deviceAlarmLogMapper.analyseByIndexName();
    }

    @Override
    public List<AnalyseVo> analyseByMonth() {
        List<AnalyseVo> analyseVos = deviceAlarmLogMapper.analyseByMonth();
        Map<String, List<AnalyseVo>> collect = analyseVos.stream().collect(Collectors.groupingBy(AnalyseVo::getGroupByName));
        List<AnalyseVo> result = getAnalyseVos(collect);
        return result;
    }

    @Override
    public List<AnalyseVo> countDeviceAnomalies() {
        return deviceAlarmLogMapper.countDeviceAnomalies();
    }

    @Override
    public List<AnalyseVo> analyseAnomaliesByMonth() {
        Map<String, List<AnalyseVo>> collect = deviceAlarmLogMapper.analyseAnomaliesByMonth().stream().collect(Collectors.groupingBy(AnalyseVo::getGroupByName));
        List<AnalyseVo> result = getAnalyseVos(collect);
        return result;
    }

    @Override
    public List<IndexLogVO> findByids(List<String> logIds) {
        return deviceAlarmLogMapper.findByids(logIds);
    }

    @Override
    public List<IndexLog> findIndexLogsByids(List<String> ids) {
        return deviceAlarmLogMapper.findIndexLogsByids(ids);
    }

    @Override
    public List<IndexLog> findIndexLogsByalarmids(List<String> alarmids) {
        return deviceAlarmLogMapper.findIndexLogsByalarmids(alarmids);
    }

    @Override
    public List<AnalyseEntity> indexAlarmAnalyse() {
        return deviceAlarmLogMapper.analyseBy("index_name");
    }

    @Override
    public List<AnalyseEntity> deviceAlarmAnalyse() {
        return deviceAlarmLogMapper.analyseBy("device_type_name");
    }

    @Override
    public boolean isHandled(String indexId) {
        return deviceAlarmLogMapper.countUnHandle(indexId) == 0;
    }

    @Override
    public DeviceAlarmLogVo getLast(String deviceId, String indexId) {
        return deviceAlarmLogMapper.getLast(deviceId, indexId);
    }

    @Override
    public TodoAnalyseEntity analyse() {
        return deviceAlarmLogMapper.analyse();
    }

    private static List<AnalyseVo> getAnalyseVos(Map<String, List<AnalyseVo>> collect) {
        LocalDate now = LocalDate.now().minusMonths(5);
        List<AnalyseVo> result = new ArrayList();
        for (int i = 0; i < 6; i++) {
            String groupByName = now.plusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM"));
            if (collect.containsKey(groupByName)) {
                result.add(collect.get(groupByName).get(0));
            } else {
                AnalyseVo analyseVo = new AnalyseVo();
                analyseVo.setGroupByName(groupByName);
                result.add(analyseVo);
            }
        }
        return result;
    }
}