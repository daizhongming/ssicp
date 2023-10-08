package com.zhilianhua.ssicp.controller;


import com.zhilianhua.ssicp.entity.db.Device;
import com.zhilianhua.ssicp.entity.enums.DeviceTypeEnum;
import com.zhilianhua.ssicp.entity.enums.ThreeDimensionPageEnum;
import com.zhilianhua.ssicp.entity.request.DeviceDTO;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.entity.request.searchparams.DeviceSearchParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DeviceService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 设备表 前端控制器
 * </p>
 *
 * @author 代忠明
 * @since 2023-06-19
 */
@RestController
@RequestMapping("/devices")
@Privilege("设备管理")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("")
    @Privilege("搜索")
    public ResponseEntity<PageResultEntity> pageList(@Valid DeviceSearchParams deviceSearchParams) {
        return ResponseEntityUtils.success(deviceService.pageList(deviceSearchParams));
    }

    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @PostMapping("")
    @Privilege(value = "新增设备")
    public ResponseEntity insert(@Valid @RequestBody DeviceDTO deviceDTO) {
        deviceService.insert(deviceDTO);
        return ResponseEntityUtils.success();
    }

    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @PutMapping("")
    @Privilege(value = "修改设备")
    public ResponseEntity update(@Valid @RequestBody DeviceDTO deviceDTO) {
        PipeAdd pipeAdd = deviceDTO.getPipeAdd();
        if (pipeAdd != null) {
            Device device = new Device();
            device.setId(pipeAdd.getPipe().getId());
            device.setParentDeviceType("4");
            device.setDeviceName(pipeAdd.getPipe().getPipeName());
            device.setDeviceCode(pipeAdd.getPipe().getDrawingCode());
            deviceDTO.setDevice(device);
        }
        deviceService.update(deviceDTO);
        return ResponseEntityUtils.success();
    }

    /**
     * 回显
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("getByid/{id}")
    @Privilege(value = "回显")
    public ResponseEntity getByid(@PathVariable String id) {
        DeviceDTO deviceDTO = deviceService.getByid(id);
        return ResponseEntityUtils.success(deviceDTO);
    }

    /**
     * 查询管段下所有设备
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("getDevicesBypId/{pid}")
    @Privilege(value = "查询管段下所有设备")
    public ResponseEntity getDevicesBypId(@PathVariable String pid) {
        return ResponseEntityUtils.success(deviceService.getDevicesBypId(pid));
    }


    /**
     * 删除
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @DeleteMapping("{id}")
    @Privilege(value = "删除")
    public ResponseEntity delete(@PathVariable String id) {
        Device device = new Device();
        device.setId(id);
        device.setDeleteFlag(1);
        deviceService.update(new DeviceDTO(device));
        return ResponseEntityUtils.success();
    }

    @Privilege("查询设备树")
    @GetMapping("/tree")
    public ResponseEntity tree() {
        return ResponseEntityUtils.success(deviceService.tree());
    }


    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/deviceData")
    @Privilege(value = "查询设备数据")
    public ResponseEntity getDeviceData(String id, String type, String page) {
        Optional<DeviceTypeEnum> deviceTypeEnumOptional = DeviceTypeEnum.getByTypeCode(type);
        Optional<ThreeDimensionPageEnum> threeDimensionPageEnumOptional = ThreeDimensionPageEnum.getByPageCode(type);
        return ResponseEntityUtils.success(deviceService.getDeviceData(id));
    }

    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/allDeviceData")
    @Privilege(value = "查询所有设备数据")
    public ResponseEntity findAllDeviceData() {
        return ResponseEntityUtils.success(deviceService.findAllDeviceData());
    }
}
