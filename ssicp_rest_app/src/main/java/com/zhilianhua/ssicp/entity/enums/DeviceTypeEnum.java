package com.zhilianhua.ssicp.entity.enums;

import com.zhilianhua.ssicp.entity.db.*;
import com.zhilianhua.ssicp.entity.request.DeviceDTO;
import com.zhilianhua.ssicp.entity.request.PipeAdd;
import com.zhilianhua.ssicp.service.DeviceService;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public enum DeviceTypeEnum {
    PIPE("pipe", "管段", "pipe_index", "4") {
        @Override
        public Optional<PipeAdd> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getPipeByid(id));
        }
    },
    VALVE("valve", "阀门", "valve_index", "2") {
        @Override
        public Optional<DeviceValve> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getValveByid(id));
        }
    },
    TRAP("trap", "疏水阀", "steam_traps_index", "3") {
        @Override
        public Optional<DeviceSteamTraps> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getSteamTrapsByid(id));
        }
    },
    INSTRUMENT("instrument", "仪表", "instrument_index", "1") {
        @Override
        public Optional<DeviceInstrument> getDeviceData(DeviceService deviceService, String id) {

            return Optional.ofNullable(deviceService.getInstrumentByid(id));
        }
    },
    PRODUCTION("production", "产汽点", "steam_production_point_index", "5") {
        @Override
        public Optional<DeviceGasPoint> getDeviceData(DeviceService deviceService, String id) {

            return Optional.ofNullable(deviceService.getGasPointByid(id));
        }
    },
    CONSUMPTION("consumption", "用汽点", "with_steam_spots_index", "6") {
        @Override
        public Optional<DeviceGasPoint> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getGasPointByid(id));
        }
    },
    COMPENSATOR("compensator", "补偿器", "compensator_index", "7") {
        @Override
        public Optional<DeviceCompensator> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getCompensatorByid(id));
        }
    },
    TEE("tee", "三通", null, "8") {
        @Override
        public Optional<DeviceElbowTree> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getElbowTreeByid(id));
        }
    },
    ELBOW("elbow", "弯头", null, "9") {
        @Override
        public Optional<DeviceElbowTree> getDeviceData(DeviceService deviceService, String id) {
            return Optional.ofNullable(deviceService.getElbowTreeByid(id));
        }
    };

    private String code;
    private String name;
    private String indexTypeCode;

    private String parentDeviceType;


    DeviceTypeEnum(String code, String name, String indexTypeCode, String parentDeviceType) {
        this.code = code;
        this.name = name;
        this.indexTypeCode = indexTypeCode;
        this.parentDeviceType = parentDeviceType;

    }

    public abstract <T> Optional<T> getDeviceData(DeviceService deviceService, String id);

    public Optional<DeviceDTO> getDeviceBase(DeviceService deviceService, String id) {
        Device device = deviceService.load(id);
        if (Objects.isNull(device)) return Optional.empty();
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDevice(device);
        return Optional.of(deviceDTO);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexTypeCode() {
        return indexTypeCode;
    }

    public void setIndexTypeCode(String indexTypeCode) {
        this.indexTypeCode = indexTypeCode;
    }

    public String getParentDeviceType() {
        return parentDeviceType;
    }

    public void setParentDeviceType(String parentDeviceType) {
        this.parentDeviceType = parentDeviceType;
    }

    public static Optional<DeviceTypeEnum> getByParentDeviceType(String parentDeviceType) {
        if (StringUtils.isBlank(parentDeviceType)) return Optional.empty();
        return Arrays.stream(DeviceTypeEnum.values()).filter(deviceTypeEnum -> StringUtils.equals(deviceTypeEnum.parentDeviceType, parentDeviceType)).findFirst();
    }

    public static Optional<DeviceTypeEnum> getByTypeCode(String typeCode) {
        if (StringUtils.isBlank(typeCode)) return Optional.empty();
        return Arrays.stream(DeviceTypeEnum.values()).filter(deviceTypeEnum -> StringUtils.equals(deviceTypeEnum.code, typeCode)).findFirst();
    }

    public static Optional<String> getNameByParentDeviceType(String parentDeviceType) {
        return getByParentDeviceType(parentDeviceType).map(DeviceTypeEnum::getName);
    }

    public static List<String> getIndexTypeCodes() {
        return Arrays.stream(DeviceTypeEnum.values()).map(DeviceTypeEnum::getIndexTypeCode).filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }
}
