package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.Pipe;
import com.zhilianhua.ssicp.entity.db.PipeDevise;
import com.zhilianhua.ssicp.entity.db.PipeInsulation;
import com.zhilianhua.ssicp.entity.db.PipeMaintenance;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.DeviceGroupAlarmVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管段表 服务类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface PipeService {

    void delete(Long id);

    List<DeviceGroupAlarmVO.Class> devicesandAlarm(String id);

    List<Map<String, String>> selectList();

    PageResultEntity pageList(PipelinePageReqSearchParams pageReqSearchParams);

    PipeAdd load(String id);

    void update(Pipe pipe);

    Pipe getByid(String id);

    void del(String id);

    void insert(Pipe pipe);

    void inserMaintenance(PipeMaintenance pipeMaintenance);

    void insertDevise(PipeDevise pipeDevise);

    void insertInsulation(PipeInsulation pipeInsulation);

    void updateDevise(PipeDevise pipeDevise);

    void updateMaintenance(PipeMaintenance pipeMaintenance);

    void updateInsulation(PipeInsulation pipeInsulation);
}
