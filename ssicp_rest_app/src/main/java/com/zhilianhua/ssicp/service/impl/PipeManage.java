package com.zhilianhua.ssicp.service.impl;

import com.alibaba.fastjson2.JSON;
import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.DeviceDTO;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.PipelinePageReqSearchParams;
import com.zhilianhua.ssicp.entity.response.*;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.service.*;
import com.zhilianhua.ssicp.utils.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PipeManage {
    @Autowired
    private PipeService pipeService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DictDataService dictDataService;

    public TreeVo getPressureLevelTree() {
        // 先获取所有压力等级
        TreeVo treeVo = new TreeVo();
        List<PipelineVo> pipelineVos = pipelineService.pressurePipelineGrouping(new ArrayList<>());
        Map<String, List<PipelineVo>> collect = pipelineVos.stream().collect(Collectors.groupingBy(PipelineVo::getDictLabel));
        ArrayList<TreeVo.Entry> entries = new ArrayList<>();
        List<DictData> pressureLevels = dictDataService.getPressureLevels();
        for (Map.Entry<String, List<PipelineVo>> stringListEntry : collect.entrySet()) {
            TreeVo.Entry entry = new TreeVo.Entry();
            List<PipelineVo> value = stringListEntry.getValue();
            String key = stringListEntry.getKey();
            entry.setTitle(key);
            entry.setExtra(pressureLevels.stream().filter(dictData -> dictData.getDictLabel().equals(key)).collect(Collectors.toList()).get(0));
            ArrayList<TreeVo.Entry> guanxians = new ArrayList<>();//管线
            for (PipelineVo pipelineVo : value) {
                TreeVo.Entry guanxian = new TreeVo.Entry();
                guanxian.setTitle(pipelineVo.getPname());
                guanxian.setId(pipelineVo.getPid());
                ArrayList<TreeVo.Entry> devices = new ArrayList<>();//设备
                TreeVo.Entry guandaun = new TreeVo.Entry();//管段
                guandaun.setTitle("管段");//缺少数量

                TreeVo.Entry wantou = new TreeVo.Entry();//弯头/三通
                wantou.setTitle("弯头/三通");//缺少数量

                TreeVo.Entry buchangqi = new TreeVo.Entry();//补偿器
                buchangqi.setTitle("补偿器");//缺少数量

                TreeVo.Entry famen = new TreeVo.Entry();//阀门
                famen.setTitle("阀门");//缺少数量

                TreeVo.Entry shushuifa = new TreeVo.Entry();//疏水阀
                shushuifa.setTitle("疏水阀");//缺少数量

                TreeVo.Entry yibiao = new TreeVo.Entry();//仪表
                yibiao.setTitle("仪表");//缺少数量

                TreeVo.Entry chanqidian = new TreeVo.Entry();//产汽点
                chanqidian.setTitle("产汽点");//缺少数量

                TreeVo.Entry yongqidian = new TreeVo.Entry();//用汽点
                yongqidian.setTitle("用汽点");//缺少数量

                Set<TreeVo.Entry> guanduans = new HashSet<>();//管段
                Set<TreeVo.Entry> yibiaos = new HashSet<>();//仪表
                Set<TreeVo.Entry> shushuifas = new HashSet<>();//疏水阀
                Set<TreeVo.Entry> famens = new HashSet<>();//阀门
                Set<TreeVo.Entry> wantous = new HashSet<>();//三通/弯头
                Set<TreeVo.Entry> buchangqis = new HashSet<>();//补偿器
                Set<TreeVo.Entry> chanqidians = new HashSet<>();//产汽点
                Set<TreeVo.Entry> yongqidians = new HashSet<>();//用汽点
                for (String pid : JSON.parseArray(pipelineVo.getPipeids(), String.class)) {
                    //查询管段
                    TreeVo.Entry dd = new TreeVo.Entry();//管段
                    Pipe pipe = pipeService.getByid(pid);
                    if (pipe != null) {
                        dd.setTitle(pipe.getPipeName());
                        dd.setId(pipe.getId());
                        guanduans.add(dd);
                    }

                    //获取设备
                    Map<String, Object> devices1 = deviceService.getDevicesBypId(pid);

                    //查询仪表
                    List<DeviceInstrumentVO> yibiao1 = (List<DeviceInstrumentVO>) devices1.get("yibiao");
                    for (DeviceInstrumentVO instrumentVO : yibiao1) {
                        TreeVo.Entry yy = new TreeVo.Entry();//仪表
                        yy.setId(instrumentVO.getId());
                        yy.setTitle(instrumentVO.getDeviceName());
                        yibiaos.add(yy);
                    }

                    //查询阀门
                    List<DeviceValveVO> famen1 = (List<DeviceValveVO>) devices1.get("famen");
                    for (DeviceValveVO valveVO : famen1) {
                        TreeVo.Entry ff = new TreeVo.Entry();//阀门
                        ff.setId(valveVO.getId());
                        ff.setTitle(valveVO.getDeviceName());
                        famens.add(ff);
                    }

                    //查询疏水阀
                    List<DeviceSteamTrapsVO> shushuifa1 = (List<DeviceSteamTrapsVO>) devices1.get("shushuifa");
                    for (DeviceSteamTrapsVO deviceSteamTrapsVO : shushuifa1) {
                        TreeVo.Entry ss = new TreeVo.Entry();//阀门
                        ss.setId(deviceSteamTrapsVO.getId());
                        ss.setTitle(deviceSteamTrapsVO.getDeviceName());
                        shushuifas.add(ss);
                    }

                    //查询三通/弯头
                    List<DeviceElbowTreeVO> wantou1 = (List<DeviceElbowTreeVO>) devices1.get("wantou");
                    for (DeviceElbowTreeVO deviceElbowTreeVO : wantou1) {
                        TreeVo.Entry ss = new TreeVo.Entry();//三通/弯头
                        ss.setTitle(deviceElbowTreeVO.getDeviceName());
                        ss.setId(deviceElbowTreeVO.getId());
                        wantous.add(ss);
                    }


                    //查询补偿器
                    List<DeviceCompensatorVO> buchangqi1 = (List<DeviceCompensatorVO>) devices1.get("buchangqi");
                    for (DeviceCompensatorVO deviceCompensatorVO : buchangqi1) {
                        TreeVo.Entry bb = new TreeVo.Entry();//补偿器
                        bb.setId(deviceCompensatorVO.getId());
                        bb.setTitle(deviceCompensatorVO.getDeviceName());
                        buchangqis.add(bb);
                    }

                    //查询产汽点
                    List<DeviceGasPointVO> chanqidian1 = (List<DeviceGasPointVO>) devices1.get("chanqidian");
                    for (DeviceGasPointVO deviceGasPointVO : chanqidian1) {
                        TreeVo.Entry cc = new TreeVo.Entry();//产汽点
                        cc.setTitle(deviceGasPointVO.getDeviceName());
                        cc.setId(deviceGasPointVO.getId());
                        chanqidians.add(cc);
                    }

                    //查询用汽点
                    List<DeviceGasPointVO> yongqidian1 = (List<DeviceGasPointVO>) devices1.get("yongqidian");
                    for (DeviceGasPointVO deviceGasPointVO : yongqidian1) {
                        TreeVo.Entry yy = new TreeVo.Entry();//产汽点
                        yy.setId(deviceGasPointVO.getId());
                        yy.setTitle(deviceGasPointVO.getDeviceName());
                        yongqidians.add(yy);
                    }
                }
                ArrayList<TreeVo.Entry> entries1 = new ArrayList<>(guanduans);
                guandaun.setChildren(entries1);
                ArrayList<TreeVo.Entry> entries2 = new ArrayList<>(yibiaos);
                yibiao.setChildren(entries2);
                ArrayList<TreeVo.Entry> entries3 = new ArrayList<>(shushuifas);
                shushuifa.setChildren(entries3);
                ArrayList<TreeVo.Entry> entries4 = new ArrayList<>(famens);
                famen.setChildren(entries4);
                ArrayList<TreeVo.Entry> entries5 = new ArrayList<>(wantous);
                wantou.setChildren(entries5);
                ArrayList<TreeVo.Entry> entries6 = new ArrayList<>(buchangqis);
                buchangqi.setChildren(entries6);
                ArrayList<TreeVo.Entry> entries7 = new ArrayList<>(chanqidians);
                chanqidian.setChildren(entries7);
                ArrayList<TreeVo.Entry> entries8 = new ArrayList<>(yongqidians);
                yongqidian.setChildren(entries8);
                devices.add(guandaun);
                devices.add(yibiao);
                devices.add(shushuifa);
                devices.add(famen);
                devices.add(wantou);
                devices.add(buchangqi);
                devices.add(chanqidian);
                devices.add(yongqidian);
                guanxian.setChildren(devices);
                guanxians.add(guanxian);
            }
            entry.setChildren(guanxians);
//            entries.add(entry);
            entries.addAll(guanxians);
        }
        treeVo.setEntryList(entries);
        return treeVo;
    }

    public List pressurePipelineGrouping(List<String> regions) {
        List<PipelineVo> pipelines = pipelineService.pressurePipelineGrouping(regions);
        Map<String, List<PipelineVo>> collect = pipelines.stream().collect(Collectors.groupingBy(PipelineVo::getDictLabel));
        ArrayList<PipelineVo.Class> classe = new ArrayList<>();
        for (Map.Entry<String, List<PipelineVo>> entry : collect.entrySet()) {
            PipelineVo.Class aClass1 = new PipelineVo.Class();
            String key = entry.getKey();
            aClass1.setTitle(key);
            ArrayList<PipelineVo.Class> classes = new ArrayList<>();
            for (PipelineVo pipelineVo : entry.getValue()) {
                PipelineVo.Class aClass2 = new PipelineVo.Class();
                aClass2.setTitle(pipelineVo.getPname());
                aClass2.setId(pipelineVo.getPid());
                ArrayList<PipelineVo.Class> classes2 = new ArrayList<>();
                List<String> pids = JSON.parseArray(pipelineVo.getPipeids(), String.class);
                for (String pid : pids) {
                    PipelineVo.Class aClass = new PipelineVo.Class();
                    Pipe byid = pipeService.getByid(pid);
                    aClass.setTitle(byid.getPipeName());
                    aClass.setId(byid.getId());
                    classes2.add(aClass);
                }
                aClass2.setChildren(classes2);
                classes.add(aClass2);
            }
            aClass1.setChildren(classes);
            classe.add(aClass1);
        }
        return classe;
    }

    public PageResultEntity pageList(PipelinePageReqSearchParams pageReqSearchParams) {
        if (StringUtils.isNoneBlank(pageReqSearchParams.getPid())) {
            pageReqSearchParams.setPipeIds(JSON.parseArray(pipelineService.getid(pageReqSearchParams.getPid()).getPipeIds(), String.class));
        }
        return pipeService.pageList(pageReqSearchParams);
    }

    public PageResultEntity<Pipeline> pageListPipeline(PipelinePageReqSearchParams pageReqDto) {
        PageResultEntity<Pipeline> pipelinePageResultEntity = pipelineService.pageList(pageReqDto);
        pipelinePageResultEntity.getData().forEach(pipeline -> {
            List<String> pids = JSON.parseArray(pipeline.getPipeIds(), String.class);
            int pipelineLength = 0;//管线长度
            int pipeCount = pids.size();//管段数量
            int elbowCount = 0;//弯头数量
            int compensatorCount = 0;//补偿器数量
            int valveCount = 0;//阀门数量
            int instrumentCount = 0;//仪表数量
            for (String pid : pids) {
                //获取管段信息
                Pipe pipe = pipeService.getByid(pid);
                pipelineLength += pipe.getPipeLength();
                //获取管段下所有设备信息
                Map<String, Object> devices = deviceService.getDevicesBypId(pid);

                List<DeviceInstrumentVO> yibiao = (List<DeviceInstrumentVO>) devices.get("yibiao");
                instrumentCount += yibiao.size();

                List<DeviceValveVO> famen = (List<DeviceValveVO>) devices.get("famen");
                valveCount += famen.size();

//                List<DeviceSteamTrapsVO> shushuifa = (List<DeviceSteamTrapsVO>) devices.get("shushuifa");

//                List<DeviceGasPointVO> chanqidian = (List<DeviceGasPointVO>) devices.get("chanqidian");

//                List<DeviceGasPointVO> yongqidian = (List<DeviceGasPointVO>) devices.get("yongqidian");

                List<DeviceCompensatorVO> buchangqi = (List<DeviceCompensatorVO>) devices.get("buchangqi");
                compensatorCount += buchangqi.size();

                List<DeviceElbowTreeVO> wantou = (List<DeviceElbowTreeVO>) devices.get("wantou");
                elbowCount += wantou.size();
            }
            pipeline.setPipelineLength((long) pipelineLength);
            pipeline.setPipeCount((long) pipeCount);
            pipeline.setElbowCount((long) elbowCount);
            pipeline.setCompensatorCount((long) compensatorCount);
            pipeline.setValveCount((long) valveCount);
            pipeline.setInstrumentCount((long) instrumentCount);

        });
        return pipelinePageResultEntity;
    }

    public void del(String id) {
        pipeService.del(id);
        Device device = new Device();
        device.setId(id);
        device.setDeleteFlag(1);
        deviceService.update(new DeviceDTO(device));
    }
}
