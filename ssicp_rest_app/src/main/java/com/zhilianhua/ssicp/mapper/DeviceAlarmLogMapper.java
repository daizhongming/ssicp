package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.DeviceAlarmLog;
import com.zhilianhua.ssicp.entity.db.IndexLog;
import com.zhilianhua.ssicp.entity.request.DeviceAlarmLogHandleRequest;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogDetailsSearchParams;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceAlarmLogSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceAlarmLogMapper {
    void add(DeviceAlarmLog deviceAlarmLog);

    PageResultEntity<DeviceAlarmLogVo> search(DeviceAlarmLogSearchParams deviceAlarmLogSearchParams);

    void handle(DeviceAlarmLogHandleRequest deviceAlarmLogHandleRequest);

    DeviceAlarmLogVo getLast(String deviceId, @Param("indexId") String indexId);

    List<DeviceAlarmLogAlarmDetailsVo> findAlarmDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams);

    List<DeviceAlarmLogHandleDetailsVo> findHandleDetails(DeviceAlarmLogDetailsSearchParams deviceAlarmLogDetailsSearchParams);

    List<AnalyseVo> analyseByIndexName();
    List<AnalyseVo> analyseByMonth();

    List<IndexLogVO> findByids(List<String> logIds);

    List<IndexLog> findIndexLogsByids(List<String> logIds);

    List<AnalyseVo> countDeviceAnomalies();

    List<AnalyseVo> analyseAnomaliesByMonth();

    List<IndexLog> findIndexLogsByalarmids(List<String> alarmids);

    List<AnalyseEntity> analyseBy(@Param("columnName") String columnName);

    int countUnHandle(@Param("indexId") String indexId);
    TodoAnalyseEntity analyse();
}