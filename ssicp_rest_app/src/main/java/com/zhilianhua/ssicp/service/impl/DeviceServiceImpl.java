package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.request.DeviceDTO;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.DeviceMapper;
import com.zhilianhua.ssicp.redis.DeviceRedisService;
import com.zhilianhua.ssicp.service.DeviceAlarmService;
import com.zhilianhua.ssicp.service.DeviceService;
import com.zhilianhua.ssicp.service.PipeService;
import com.zhilianhua.ssicp.service.PipelineService;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 设备表 服务实现类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private PipeService pipeService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private DeviceAlarmService deviceAlarmService;
    @Autowired
    private DeviceRedisService deviceRedisService;

    @Override
    public List<Map<String, String>> selectList(String i) {
        return deviceMapper.selectList(i);
    }

    @Override
    public void saveBatch(List<Device> devices) {
        deviceMapper.saveBatch(devices);
    }

    @Override
    public PageResultEntity pageList(DeviceSearchParams deviceSearchParams) {
        if (deviceSearchParams.getParentDeviceType().equals(Device.YIBIAO)) {
            return deviceMapper.pageListInstruments(deviceSearchParams);
        } else if (deviceSearchParams.getParentDeviceType().equals(Device.FAMEN)) {
            return deviceMapper.pageListValves(deviceSearchParams);
        } else if (deviceSearchParams.getParentDeviceType().equals(Device.SHUSHUIFA)) {
            return deviceMapper.pageListSteamTraps(deviceSearchParams);
        } else if (deviceSearchParams.getParentDeviceType().equals(Device.CHANQIDIAN) || deviceSearchParams.getParentDeviceType().equals(Device.CHANYONGQI) || deviceSearchParams.getParentDeviceType().equals(Device.YONGQIDIAN)) {
            return deviceMapper.pageListGasPoint(deviceSearchParams);
        } else if (deviceSearchParams.getParentDeviceType().equals(Device.BUCHANGQI)) {
            return deviceMapper.pageListCompensators(deviceSearchParams);
        } else if (deviceSearchParams.getParentDeviceType().equals(Device.SANTONG) || deviceSearchParams.getParentDeviceType().equals(Device.WANTOU)) {
            return deviceMapper.pageListElbowTree(deviceSearchParams);
        }
        return null;
    }

    public boolean areAllFieldsNull(Object obj) {
        String contents = ReflectionToStringBuilder.toString(obj);
        return StringUtils.equals(contents, "{}");
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(PipeAdd pipeAdd) {
        //添加管段信息
        Pipe pipe = pipeAdd.getPipe();
        pipeService.insert(pipe);
        //添加维护信息
        PipeMaintenance pipeMaintenance = pipeAdd.getPipeMaintenance();
        if (!areAllFieldsNull(pipeMaintenance)) {
            pipeMaintenance.setPipeId(pipe.getId());
            pipeService.inserMaintenance(pipeMaintenance);
        }

        //添加设计信息
        PipeDevise pipeDevise = pipeAdd.getPipeDevise();
        if (!areAllFieldsNull(pipeDevise)) {
            pipeDevise.setPipeId(pipe.getId());
            pipeService.insertDevise(pipeDevise);
        }

        //添加保温层信息
        PipeInsulation pipeInsulation = pipeAdd.getPipeInsulation();
        pipeInsulation.setPipeId(pipe.getId());
        pipeService.insertInsulation(pipeInsulation);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(PipeAdd pipeAdd) {
        //修改管线信息
        Pipe pipe = pipeAdd.getPipe();
        if (!areAllFieldsNull(pipe)) {
            pipeService.update(pipe);
        }
        //修改维护信息
        PipeMaintenance pipeMaintenance = pipeAdd.getPipeMaintenance();
        if (pipeMaintenance != null && !areAllFieldsNull(pipeMaintenance)) {
            if (pipeMaintenance.getId() != null) {
                pipeService.updateMaintenance(pipeMaintenance);
            } else {
                pipeMaintenance.setPipeId(pipe.getId());
                pipeService.inserMaintenance(pipeMaintenance);
            }
        }

        //修改设计信息
        PipeDevise pipeDevise = pipeAdd.getPipeDevise();
        if (pipeDevise != null && !areAllFieldsNull(pipeDevise)) {
            if (pipeDevise.getId() != null) {
                pipeService.updateDevise(pipeDevise);
            } else {
                pipeDevise.setPipeId(pipe.getId());
                pipeService.insertDevise(pipeDevise);
            }

        }

        //修改保温层信息
        PipeInsulation pipeInsulation = pipeAdd.getPipeInsulation();
        if (pipeInsulation != null && !areAllFieldsNull(pipeInsulation)) {
            if (pipeInsulation.getId() != null) {
                pipeService.updateInsulation(pipeInsulation);
            } else {
                pipeInsulation.setPipeId(pipe.getId());
                pipeService.insertInsulation(pipeInsulation);
            }
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(DeviceDTO deviceDTO) {
        //新增设备
        Device device = deviceDTO.getDevice();
        device.setId(IdUtils.uuid());
        deviceMapper.saveBatch(Collections.singletonList(device));

        //新增管段
        PipeAdd pipeAdd = deviceDTO.getPipeAdd();
        if (pipeAdd != null) {
            pipeAdd.getPipe().setId(device.getId());
            insert(pipeAdd);
        }

        //新增仪表
        DeviceInstrument deviceInstrument = deviceDTO.getDeviceInstrument();
        if (deviceInstrument != null) {
            deviceInstrument.setId(device.getId());
            deviceMapper.insertInstrument(deviceInstrument);
        }

        //新增阀门
        DeviceValve deviceValve = deviceDTO.getDeviceValve();
        if (deviceValve != null) {
            deviceValve.setId(device.getId());
            deviceMapper.insertValve(deviceValve);
        }

        //新增补偿器
        DeviceCompensator deviceCompensator = deviceDTO.getDeviceCompensator();
        if (deviceCompensator != null) {
            deviceCompensator.setId(device.getId());
            deviceMapper.insertCompensator(deviceCompensator);
        }

        //添加疏水阀
        DeviceSteamTraps deviceSteamTraps = deviceDTO.getDeviceSteamTraps();
        if (deviceSteamTraps != null) {
            deviceSteamTraps.setId(device.getId());
            deviceMapper.insertSteamTraps(deviceSteamTraps);
        }
        //添加弯头/三通
        DeviceElbowTree deviceElbowTree = deviceDTO.getDeviceElbowTree();
        if (deviceElbowTree != null) {
            deviceElbowTree.setId(device.getId());
            deviceMapper.insertElbowTree(deviceElbowTree);
        }
        //添加产用汽点
        DeviceGasPoint deviceGasPoint = deviceDTO.getDeviceGasPoint();
        if (deviceGasPoint != null) {
            deviceGasPoint.setId(device.getId());
            deviceMapper.insertGasPoint(deviceGasPoint);
        }


        //添加指标集合
        List<DeviceAlarm> deviceAlarmList = deviceDTO.getDeviceAlarmList();
        if (deviceAlarmList != null && deviceAlarmList.size() > 0) {
            deviceAlarmList.forEach(deviceAlarm -> deviceAlarm.setId(IdUtils.uuid()));
            deviceAlarmList.forEach(deviceAlarm -> deviceAlarm.setDeviceId(device.getId()));
            deviceAlarmService.saveBatch(deviceAlarmList);
        }
        updateDeviceBaseDataCache(deviceDTO.getDevice().getId());
    }

    @Override
    public List<PipelineAndPipe> getpipeBydid(String did) {
        List<PipelineAndPipe> pipelineAndPipes = new ArrayList<>();
        //查询设备
        Device device = deviceMapper.getByid(did);
        //判断设备类型
        if (device.getParentDeviceType().equals(Device.YIBIAO)) {//仪表
            //查询仪表
            DeviceInstrument deviceInstrument = deviceMapper.getInstrumentByid(did);
            //整合出口
            Pipe pipe = pipeService.getByid(deviceInstrument.getPipeExit());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));
            //整合入口
            Pipe pipe1 = pipeService.getByid(deviceInstrument.getPipeInlet());
            List<Pipeline> pipelines1 = pipelineService.getBypid(pipe1.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines1, pipe1));

        } else if (device.getParentDeviceType().equals(Device.FAMEN)) {//阀门
            //查询阀门
            DeviceValve valve = deviceMapper.getValveByid(did);
            //整合出口
            Pipe pipe = pipeService.getByid(valve.getPipeExit());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));
            //整合入口
            Pipe pipe1 = pipeService.getByid(valve.getPipeInlet());
            List<Pipeline> pipelines1 = pipelineService.getBypid(pipe1.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines1, pipe1));
        } else if (device.getParentDeviceType().equals(Device.SHUSHUIFA)) {//疏水阀
            //查询疏水阀
            DeviceSteamTraps steamTraps = deviceMapper.getSteamTrapsByid(did);
            //整合所属管段信息
            Pipe pipe = pipeService.getByid(steamTraps.getPid());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));

        } else if (device.getParentDeviceType().equals(Device.GUANDUAN)) {//管段
            Pipe pipe = pipeService.getByid(device.getId());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));
        } else if (device.getParentDeviceType().equals(Device.CHANYONGQI) || device.getParentDeviceType().equals(Device.YONGQIDIAN) || device.getParentDeviceType().equals(Device.CHANQIDIAN)) {//产气点
            //查询产用汽点
            DeviceGasPoint gasPoint = deviceMapper.getGasPointByid(did);
            //整合所属管段信息
            Pipe pipe = pipeService.getByid(gasPoint.getPid());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));
        } else if (device.getParentDeviceType().equals(Device.BUCHANGQI)) {//补偿器
            //查询补偿器
            DeviceCompensator compensator = deviceMapper.getCompensatorByid(did);
            //整合出口
            Pipe pipe = pipeService.getByid(compensator.getPipeExit());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));
            //整合入口
            Pipe pipe1 = pipeService.getByid(compensator.getPipeInlet());
            List<Pipeline> pipelines1 = pipelineService.getBypid(pipe1.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines1, pipe1));

        } else if (device.getParentDeviceType().equals(Device.SANTONG) || device.getParentDeviceType().equals(Device.WANTOU)) {//三通
            //查询弯头/三通
            DeviceElbowTree elbowTree = deviceMapper.getElbowTreeByid(did);
            //整合出口
            Pipe pipe = pipeService.getByid(elbowTree.getPipeExit());
            List<Pipeline> pipelines = pipelineService.getBypid(pipe.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines, pipe));
            //整合入口
            Pipe pipe1 = pipeService.getByid(elbowTree.getPipeInlet());
            List<Pipeline> pipelines1 = pipelineService.getBypid(pipe1.getId());
            pipelineAndPipes.add(new PipelineAndPipe(pipelines1, pipe1));
        }
        return pipelineAndPipes;
    }

    @Override
    public Map<String, Object> getDevicesBypId(String pid) {
        Map<String, Object> map = new HashMap<>();

        //仪表
        List<DeviceInstrumentVO> deviceInstruments = deviceMapper.getInstrumentBypid(pid);
        map.put("yibiao", deviceInstruments);

        //阀门
        List<DeviceValveVO> deviceValves = deviceMapper.getValveBypid(pid);
        map.put("famen", deviceValves);

        //疏水阀
        List<DeviceSteamTrapsVO> deviceSteamTraps = deviceMapper.getSteamTrapBypid(pid);
        map.put("shushuifa", deviceSteamTraps);

        //弯头/三通
        List<DeviceElbowTreeVO> deviceElbowTrees = deviceMapper.getElbowTreeBypid(pid);
        map.put("wantou", deviceElbowTrees);

        //补偿器
        List<DeviceCompensatorVO> deviceCompensators = deviceMapper.getCompensatorBypid(pid);
        map.put("buchangqi", deviceCompensators);

        //产汽点
        List<DeviceGasPointVO> deviceGasPoints = deviceMapper.getGasPointBypid('5', pid);
        map.put("chanqidian", deviceGasPoints);

        //用汽点
        List<DeviceGasPointVO> deviceGasPoints1 = deviceMapper.getGasPointBypid('6', pid);
        map.put("yongqidian", deviceGasPoints1);

        return map;
    }

    @Override
    public DeviceDTO getByid(String id) {
        DeviceDTO deviceDTO = new DeviceDTO();
        Device device = deviceMapper.getByid(id);
        deviceDTO.setDevice(device);
        if (device.getParentDeviceType().equals(Device.YIBIAO)) {
            //查仪表
            DeviceInstrument instrumentByid = deviceMapper.getInstrumentByid(id);
            deviceDTO.setDeviceInstrument(instrumentByid);
        } else if (device.getParentDeviceType().equals(Device.FAMEN)) {
            //查阀门
            DeviceValve deviceValve = deviceMapper.getValveByid(id);
            deviceDTO.setDeviceValve(deviceValve);
        } else if (device.getParentDeviceType().equals(Device.BUCHANGQI)) {
            //查补偿器
            DeviceCompensator deviceCompensator = deviceMapper.getCompensatorByid(id);
            deviceDTO.setDeviceCompensator(deviceCompensator);
        } else if (device.getParentDeviceType().equals(Device.SHUSHUIFA)) {
            //查疏水阀
            DeviceSteamTraps deviceSteamTraps = deviceMapper.getSteamTrapsByid(id);
            deviceDTO.setDeviceSteamTraps(deviceSteamTraps);
        } else if (device.getParentDeviceType().equals(Device.SANTONG) || device.getParentDeviceType().equals(Device.WANTOU)) {
            //查询弯头/三通
            DeviceElbowTree deviceElbowTree = deviceMapper.getElbowTreeByid(id);
            deviceDTO.setDeviceElbowTree(deviceElbowTree);
        } else if (device.getParentDeviceType().equals(Device.CHANQIDIAN) || device.getParentDeviceType().equals(Device.CHANYONGQI) || device.getParentDeviceType().equals(Device.YONGQIDIAN)) {
            //查询产用汽点
            DeviceGasPoint deviceGasPoint = deviceMapper.getGasPointByid(id);
            deviceDTO.setDeviceGasPoint(deviceGasPoint);
        } else if (device.getParentDeviceType().equals(Device.GUANDUAN)) {
            //查管段
            PipeAdd load = pipeService.load(id);
            deviceDTO.setPipeAdd(load);
        }
        List<DeviceAlarm> deviceAlarms = deviceAlarmService.selectBydid(device.getId());
        deviceDTO.setDeviceAlarmList(deviceAlarms);
        return deviceDTO;
    }

    @Override
    public Device load(String id) {
        return deviceMapper.getByid(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DeviceDTO deviceDTO) {
        //修改设备信息
        Device device = deviceDTO.getDevice();
        deviceMapper.update(device);

        PipeAdd pipeAdd = deviceDTO.getPipeAdd();
        if (pipeAdd != null) {
            //修改管段信息
            update(pipeAdd);
        }

        DeviceInstrument deviceInstrument = deviceDTO.getDeviceInstrument();
        if (deviceInstrument != null) {
            //改仪表
            deviceMapper.updateInstrument(deviceInstrument);
        }
        DeviceValve deviceValve = deviceDTO.getDeviceValve();
        if (deviceValve != null) {
            //改阀门
            deviceMapper.updateValve(deviceValve);
        }
        DeviceCompensator deviceCompensator = deviceDTO.getDeviceCompensator();
        if (deviceCompensator != null) {
            //改补偿器
            deviceMapper.updateCompensator(deviceCompensator);
        }
        DeviceSteamTraps deviceSteamTraps = deviceDTO.getDeviceSteamTraps();
        if (deviceSteamTraps != null) {
            //修改疏水阀
            deviceMapper.updateSteamTraps(deviceSteamTraps);
        }
        DeviceElbowTree deviceElbowTree = deviceDTO.getDeviceElbowTree();
        if (deviceElbowTree != null) {
            //修改弯头/三通
            deviceMapper.updateElbowTree(deviceElbowTree);
        }
        DeviceGasPoint deviceGasPoint = deviceDTO.getDeviceGasPoint();
        if (deviceGasPoint != null) {
            //修改产用汽点
            deviceMapper.updateGasPoint(deviceGasPoint);
        }
        //修改指标
        List<DeviceAlarm> deviceAlarmList = deviceDTO.getDeviceAlarmList();
        deviceAlarmService.updateBatch(deviceAlarmList, device.getId());
        updateDeviceBaseDataCache(deviceDTO.getDevice().getId());
    }

    @Override
    public List<AnalyseEntity> deviceTypeCountAnalyse() {
        return findAll().stream().map(Device::getParentDeviceType)
                .map(DeviceTypeEnum::getNameByParentDeviceType)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.groupingBy(typeName -> typeName, Collectors.counting()))
                .entrySet().stream().map(entry -> {
                    AnalyseEntity analyseEntity = new AnalyseEntity();
                    analyseEntity.setType(entry.getKey());
                    analyseEntity.setCount(entry.getValue().intValue());
                    return analyseEntity;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map tree() {
        Map result = new HashMap();
        result.put("pipelines", pipelineService.findAll());
        result.put("pipes", deviceMapper.findAllPipes());
        result.put("valves", deviceMapper.findAllValves());
        result.put("traps", deviceMapper.findAllSteamTraps());
        result.put("instruments", deviceMapper.findAllInstruments());
        result.put("productions", deviceMapper.findAllProduction());
        result.put("consumptions", deviceMapper.findAllConsumption());
        result.put("compensators", deviceMapper.findAllCompensators());
        result.put("tees", deviceMapper.findAllTees());
        result.put("elbows", deviceMapper.findAllElbows());
        return result;
    }

    @Override
    public List<Device> findAll() {
        return deviceMapper.findAll();
    }

    @Override
    public DeviceSteamTraps getSteamTrapsByid(String id) {
        return deviceMapper.getSteamTrapsByid(id);
    }

    @Override
    public DeviceElbowTree getElbowTreeByid(String id) {
        return deviceMapper.getElbowTreeByid(id);
    }

    @Override
    public PipeAdd getPipeByid(String id) {
        return pipeService.load(id);
    }

    @Override
    public DeviceGasPoint getGasPointByid(String id) {
        return deviceMapper.getGasPointByid(id);
    }

    @Override
    public DeviceCompensator getCompensatorByid(String id) {
        return deviceMapper.getCompensatorByid(id);
    }

    @Override
    public DeviceValve getValveByid(String id) {
        return deviceMapper.getValveByid(id);
    }

    @Override
    public DeviceInstrument getInstrumentByid(String id) {
        return deviceMapper.getInstrumentByid(id);
    }

    @Override
    public List<DeviceGasPointVO> findAllProduction() {
        return deviceMapper.findAllProduction();
    }

    @Override
    public List<DeviceGasPointVO> findAllConsumption() {
        return deviceMapper.findAllConsumption();
    }

    @Override
    public Map<String, DeviceDataVo> findAllDeviceData() {
        return deviceRedisService.getAllDeviceData();
    }

    @Override
    public void initDeviceDataCache() {
        // 查询数据库
        deviceRedisService.deleteAllDeviceData();
        List<Device> all = findAll();
        all.forEach(device -> getAndCacheBaseData(device.getId()));
    }


    @Override
    public DeviceDataVo getDeviceData(String id) {
        DeviceDataVo deviceDataVo = deviceRedisService.getDeviceData(id);
        if (Objects.isNull(deviceDataVo)) return null;
        // 查询实时数据
        Map<String, DeviceDataVo.RealTimeData> realTimeData = deviceRedisService.getDeviceRealTimeData(id);
        deviceDataVo.setRealTimeData(realTimeData);
        return deviceDataVo;
    }

    private DeviceDataVo getAndCacheBaseData(String id) {
        Device device = load(id);
        if (Objects.isNull(device)) return null;
        Optional<DeviceTypeEnum> deviceTypeEnumOptional = DeviceTypeEnum.getByParentDeviceType(device.getParentDeviceType());
        if (!deviceTypeEnumOptional.isPresent()) return null;
        DeviceTypeEnum deviceTypeEnum = deviceTypeEnumOptional.get();
        Optional<Object> deviceData = deviceTypeEnum.getDeviceData(this, id);
        if (!deviceData.isPresent()) return null;
        DeviceDataVo deviceDataVo = new DeviceDataVo();
        deviceDataVo.setDevice(device);
        deviceDataVo.setExtra(deviceData);
        deviceRedisService.setDeviceData(id, deviceDataVo);
        return deviceDataVo;
    }

    private void updateDeviceBaseDataCache(String deviceId) {
        deviceRedisService.deleteDeviceData(deviceId);
        getAndCacheBaseData(deviceId);
    }
}
