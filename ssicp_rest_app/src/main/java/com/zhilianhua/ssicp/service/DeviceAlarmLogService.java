package com.zhilianhua.ssicp.service;


import com.zhilianhua.ssicp.entity.db.DeviceAlarmLog;
import com.zhilianhua.ssicp.entity.db.IndexLog;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import com.zhilianhua.ssicp.entity.request.DeviceAlarmLogHandleRequest;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogDetailsSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;

public interface DeviceAlarmLogService {

    void add(DeviceAlarmLog deviceAlarmLog);

    PageResultEntity<DeviceAlarmLogVo> search(DeviceAlarmLogSearchParams deviceAlarmLogSearchParams);

    void handle(DeviceAlarmLogHandleRequest deviceAlarmLogSearchParams);

    List<DeviceAlarmLogAlarmDetailsVo> findAlarmDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams);

    List<DeviceAlarmLogHandleDetailsVo> findHandleDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams);

    List<AnalyseVo> analyseByIndexName();

    List<AnalyseVo> analyseByMonth();

    List<AnalyseVo> countDeviceAnomalies();

    List<AnalyseVo> analyseAnomaliesByMonth();

    List<IndexLogVO> findByids(List<String> logIds);

    List<IndexLog> findIndexLogsByids(List<String> ids);

    List<IndexLog> findIndexLogsByalarmids(List<String> alarmids);

    List<AnalyseEntity> indexAlarmAnalyse();

    List<AnalyseEntity> deviceAlarmAnalyse();

    boolean isHandled(String indexId);

    DeviceAlarmLogVo getLast(String deviceId, String indexId);

    TodoAnalyseEntity analyse();
}
