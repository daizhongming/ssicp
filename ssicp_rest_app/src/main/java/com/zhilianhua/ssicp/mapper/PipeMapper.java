package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.db.Pipe;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.DeviceGroupAlarmVO;
import com.zhilianhua.ssicp.entity.response.PipeVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管段表 Mapper 接口
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface PipeMapper {
    void updatedel(@Param("id") Long id);

    List<DeviceGroupAlarmVO> devicesandAlarm(@Param("id") String id);

    List<Map<String, String>> selectList();

    PageResultEntity<PipeVO> pageList(PipelinePageReqSearchParams pageReqSearchParams);

    void insert(Pipe pipe);

    void inserMaintenance(PipeMaintenance pipeMaintenance);

    void insertDevise(PipeDevise pipeDevise);

    void insertInsulation(PipeInsulation pipeInsulation);

    Pipe getByid(@Param("id") String id);

    PipeMaintenance getMaintenanceBypipeId(@Param("pipeId") String pipeId);

    PipeDevise getDeviseBypipeId(@Param("pipeId") String pipeId);

    PipeInsulation getInsulationBypipeId(@Param("pipeId") String pipeId);

    List<DeviceAlarm> getDeviceAlarmListBypipeId(@Param("deviceId") String deviceId);

    void update(Pipe pipe);

    void updateMaintenance(PipeMaintenance pipeMaintenance);

    void updateDevise(PipeDevise pipeDevise);

    void updateInsulation(PipeInsulation pipeInsulation);
}
