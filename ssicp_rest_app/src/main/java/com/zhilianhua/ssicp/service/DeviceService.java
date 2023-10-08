package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.DeviceDTO;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceSearchParams;
import com.zhilianhua.ssicp.entity.response.AnalyseEntity;
import com.zhilianhua.ssicp.entity.response.DeviceDataVo;
import com.zhilianhua.ssicp.entity.response.DeviceGasPointVO;
import com.zhilianhua.ssicp.entity.response.PipelineAndPipe;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备表 服务类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface DeviceService {
    List<Map<String, String>> selectList(String i);

    void saveBatch(List<Device> devices);

    PageResultEntity pageList(DeviceSearchParams deviceSearchParams);

    void insert(DeviceDTO deviceDTO);

    List<PipelineAndPipe> getpipeBydid(String did);

    Map<String, Object> getDevicesBypId(String pid);

    DeviceDTO getByid(String id);

    Device load(String id);

    void update(DeviceDTO deviceDTO);

    List<AnalyseEntity> deviceTypeCountAnalyse();

    Map tree();

    List<Device> findAll();

    DeviceDataVo getDeviceData(String id);

    DeviceSteamTraps getSteamTrapsByid(String id);

    DeviceElbowTree getElbowTreeByid(String id);

    PipeAdd getPipeByid(String id);

    DeviceGasPoint getGasPointByid(String id);

    DeviceCompensator getCompensatorByid(String id);

    DeviceValve getValveByid(String id);

    DeviceInstrument getInstrumentByid(String id);
    List<DeviceGasPointVO> findAllProduction();
    List<DeviceGasPointVO> findAllConsumption();

    Map<String, DeviceDataVo> findAllDeviceData();
    void initDeviceDataCache();
}
