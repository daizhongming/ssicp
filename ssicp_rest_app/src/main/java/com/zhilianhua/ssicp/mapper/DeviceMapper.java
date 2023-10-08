package com.zhilianhua.ssicp.mapper;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设备表 Mapper 接口
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
public interface DeviceMapper {

    List<Map<String, String>> selectList(@Param("i") String i);

    void saveBatch(List<Device> devices);

    PageResultEntity<DeviceInstrumentVO> pageListInstruments(DeviceSearchParams deviceSearchParams);

    Device getByid(@Param("id") String id);

    DeviceInstrument getInstrumentByid(@Param("id") String id);

    void insertInstrument(DeviceInstrument deviceInstrument);

    void update(Device device);

    void updateInstrument(DeviceInstrument deviceInstrument);

    PageResultEntity<DeviceValveVO> pageListValves(DeviceSearchParams deviceSearchParams);

    void insertValve(DeviceValve deviceValve);

    DeviceValve getValveByid(@Param("id") String id);

    void updateValve(DeviceValve deviceValve);

    PageResultEntity<DeviceCompensatorVO> pageListCompensators(DeviceSearchParams deviceSearchParams);

    void insertCompensator(DeviceCompensator deviceCompensator);

    DeviceCompensator getCompensatorByid(@Param("id") String id);

    void updateCompensator(DeviceCompensator deviceCompensator);

    List<AnalyseEntity> deviceCountAnalyse();

    PageResultEntity<DeviceSteamTrapsVO> pageListSteamTraps(DeviceSearchParams deviceSearchParams);

    void insertSteamTraps(DeviceSteamTraps deviceSteamTraps);

    DeviceSteamTraps getSteamTrapsByid(@Param("id") String id);

    void updateSteamTraps(DeviceSteamTraps deviceSteamTraps);

    PageResultEntity<DeviceElbowTreeVO> pageListElbowTree(DeviceSearchParams deviceSearchParams);

    void insertElbowTree(DeviceElbowTree deviceElbowTree);

    DeviceElbowTree getElbowTreeByid(@Param("id") String id);

    void updateElbowTree(DeviceElbowTree deviceElbowTree);

    PageResultEntity<DeviceGasPointVO> pageListGasPoint(DeviceSearchParams deviceSearchParams);

    void insertGasPoint(DeviceGasPoint deviceGasPoint);

    DeviceGasPoint getGasPointByid(@Param("id") String id);

    void updateGasPoint(DeviceGasPoint deviceGasPoint);

    List<DeviceInstrumentVO> getInstrumentBypid(@Param("pid") String pid);

    List<DeviceValveVO> getValveBypid(@Param("pid") String pid);

    List<DeviceSteamTrapsVO> getSteamTrapBypid(@Param("pid") String pid);

    List<DeviceElbowTreeVO> getElbowTreeBypid(@Param("pid") String pid);

    List<DeviceCompensatorVO> getCompensatorBypid(String pid);

    List<DeviceGasPointVO> getGasPointBypid(@Param("c") char c, @Param("pid") String pid);

    List<Device> findAll();

    List<Pipe> findAllPipes();

    List<DeviceInstrumentVO> findAllInstruments();

    List<DeviceValveVO> findAllValves();

    List<DeviceCompensatorVO> findAllCompensators();

    List<DeviceSteamTrapsVO> findAllSteamTraps();

    List<DeviceElbowTreeVO> findAllElbows();

    List<DeviceElbowTreeVO> findAllTees();

    List<DeviceGasPointVO> findAllProduction();
    List<DeviceGasPointVO> findAllConsumption();
}
