package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.DeviceGroupAlarmVO;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.mapper.PipeMapper;
import com.zhilianhua.ssicp.service.PipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 管段表 服务实现类
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@Service
public class PipeServiceImpl implements PipeService {
    @Autowired
    private PipeMapper pipeMapper;

    @Override
    public void delete(Long id) {
        //逻辑删除管段
        pipeMapper.updatedel(id);
    }

    @Override
    public List<DeviceGroupAlarmVO.Class> devicesandAlarm(String id) {
        ArrayList<DeviceGroupAlarmVO.Class> classes = new ArrayList<>();
        List<DeviceGroupAlarmVO> deviceGroupAlarmVOS = pipeMapper.devicesandAlarm(id);
        for (Map.Entry<String, List<DeviceGroupAlarmVO>> stringListEntry : deviceGroupAlarmVOS.stream().collect(Collectors.groupingBy(DeviceGroupAlarmVO::getDid)).entrySet()) {
            DeviceGroupAlarmVO.Class aClass = new DeviceGroupAlarmVO.Class();
            if (stringListEntry.getValue().size() == 0) {
                continue;
            }
            aClass.setId(stringListEntry.getKey());
            aClass.setTitle(stringListEntry.getValue().get(0).getDname());
            aClass.setChildren(stringListEntry.getValue().stream().map(deviceGroupAlarmVO -> {
                DeviceGroupAlarmVO.Class aClass1 = new DeviceGroupAlarmVO.Class();
                aClass1.setId(deviceGroupAlarmVO.getIndexid());
                aClass1.setTitle(deviceGroupAlarmVO.getIndexname());
                return aClass1;
            }).collect(Collectors.toList()));
            classes.add(aClass);
        }


//        Map<String, List<DeviceGroupAlarmVO>> collect = deviceGroupAlarmVOS.stream().collect(Collectors.groupingBy(DeviceGroupAlarmVO::getParentDeviceType));
//        for (Map.Entry<String, List<DeviceGroupAlarmVO>> stringListEntry : collect.entrySet()) {
//            List<DeviceGroupAlarmVO.Class> classes1 = new ArrayList<>();
//            DeviceGroupAlarmVO.Class aClass = new DeviceGroupAlarmVO.Class();
//            aClass.setTitle(stringListEntry.getKey());
//            for (Map.Entry<String, List<DeviceGroupAlarmVO>> listEntry : stringListEntry.getValue().stream().collect(Collectors.groupingBy(DeviceGroupAlarmVO::getDid)).entrySet()) {
//                DeviceGroupAlarmVO.Class aClass1 = new DeviceGroupAlarmVO.Class();
//                aClass1.setId(listEntry.getKey());
//                aClass1.setTitle(listEntry.getValue().get(0).getDname());
//                aClass1.setChildren(listEntry.getValue().stream().map(deviceGroupAlarmVO -> {
//                    DeviceGroupAlarmVO.Class aClass2 = new DeviceGroupAlarmVO.Class();
//                    aClass2.setTitle(deviceGroupAlarmVO.getIndexname());
//                    aClass2.setId(deviceGroupAlarmVO.getIndexid());
//                    return aClass2;
//                }).collect(Collectors.toList()));
//            classes1.add(aClass1);
//            }
//            aClass.setChildren(classes1);
//            classes.add(aClass);
//        }

        //        List<String> types = deviceGroupAlarmVOS.stream().map(DeviceGroupAlarmVO::getParentDeviceType).collect(Collectors.toList());
//        Map<String, List<DeviceGroupAlarmVO>> collect = deviceGroupAlarmVOS.stream().collect(Collectors.groupingBy(DeviceGroupAlarmVO::getDid));
        //        Map<String, Object> map = new HashMap<>();
//        map.put("devicetype",types);
//        map.put("devicetype",collect);
        return classes;
    }

    @Override
    public List<Map<String, String>> selectList() {
        return pipeMapper.selectList();
    }

    @Override
    public PageResultEntity pageList(PipelinePageReqSearchParams pageReqSearchParams) {
        return pipeMapper.pageList(pageReqSearchParams);
    }

    @Override
    public PipeAdd load(String id) {
        PipeAdd pipeAdd = new PipeAdd();
        Pipe pipe = pipeMapper.getByid(id);
        //查询管段信息
        pipeAdd.setPipe(pipe);

        //查询维护信息
        pipeAdd.setPipeMaintenance(pipeMapper.getMaintenanceBypipeId(id));

        //查询设计信息
        pipeAdd.setPipeDevise(pipeMapper.getDeviseBypipeId(id));

        //查询保温层信息
        pipeAdd.setPipeInsulation(pipeMapper.getInsulationBypipeId(id));
        return pipeAdd;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Pipe pipe) {
        pipeMapper.update(pipe);
    }

    @Override
    public Pipe getByid(String id) {
        return pipeMapper.getByid(id);
    }

    @Override
    public void del(String id) {
        Pipe pipe = new Pipe();
        pipe.setId(id);
        pipe.setDeleteFlag(1);
        pipeMapper.update(pipe);
    }

    @Override
    public void insert(Pipe pipe) {
        pipeMapper.insert(pipe);
    }

    @Override
    public void inserMaintenance(PipeMaintenance pipeMaintenance) {
        pipeMapper.inserMaintenance(pipeMaintenance);
    }

    @Override
    public void insertDevise(PipeDevise pipeDevise) {
        pipeMapper.insertDevise(pipeDevise);
    }

    @Override
    public void insertInsulation(PipeInsulation pipeInsulation) {
        pipeMapper.insertInsulation(pipeInsulation);
    }

    @Override
    public void updateDevise(PipeDevise pipeDevise) {
        pipeMapper.updateDevise(pipeDevise);
    }

    @Override
    public void updateMaintenance(PipeMaintenance pipeMaintenance) {
        pipeMapper.updateMaintenance(pipeMaintenance);
    }

    @Override
    public void updateInsulation(PipeInsulation pipeInsulation) {
        pipeMapper.updateInsulation(pipeInsulation);
    }
}

