package com.zhilianhua.ssicp.redis;

import com.zhilianhua.ssicp.entity.response.DeviceDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 基本数据
 * ssicp::device::baseData  id  {}
 * 实时数据
 * ssicp::device::indexData  id::指标名 {}
 */
@Service
public class DeviceRedisService extends RedisBaseService {
    private static final String DEVICE_PREFIX = "device";
    private static final String DEVICE_INDEX_DATA_PREFIX = "indexData";
    private static final String DEVICE_BASE_DATA_PREFIX = "baseData";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    private String getDeviceIndexDataRedisKey() {
        return DEVICE_PREFIX + KEY_SPLITTER + DEVICE_INDEX_DATA_PREFIX;
    }

    private String getDeviceIndexDataRedisField(String id, String indexName) {
        return id + KEY_SPLITTER + indexName;
    }

    public void setDeviceRealTimeData(String id, String indexName, DeviceDataVo.RealTimeData realTimeData) {
        setHash(getDeviceIndexDataRedisKey(), getDeviceIndexDataRedisField(id, indexName), realTimeData);
    }

    public Map<String, DeviceDataVo.RealTimeData> getDeviceRealTimeData(String id) {
        return getHashWithFieldPrefix(getDeviceIndexDataRedisKey(), id, DeviceDataVo.RealTimeData.class);
    }


    private String getDeviceBaseDataRedisKey() {
        return DEVICE_PREFIX + KEY_SPLITTER + DEVICE_BASE_DATA_PREFIX;
    }

    public DeviceDataVo getDeviceData(String id) {
        return getHashValue(getDeviceBaseDataRedisKey(), id, DeviceDataVo.class);
    }

    public void deleteDeviceData(String id) {
        deleteHashValue(getDeviceBaseDataRedisKey(), id);
    }

    public void deleteAllDeviceData() {
        deleteValue(getDeviceBaseDataRedisKey());
    }

    public void setDeviceData(String id, DeviceDataVo deviceDataVo) {
        setHashValue(getDeviceBaseDataRedisKey(), id, deviceDataVo);
    }

    public Map<String, DeviceDataVo> getAllDeviceData() {
        Map<String, DeviceDataVo> baseDataMap = getHash(getDeviceBaseDataRedisKey(), DeviceDataVo.class);
        Map<String, DeviceDataVo.RealTimeData> indexDataMap = getHash(getDeviceIndexDataRedisKey(), DeviceDataVo.RealTimeData.class);
        indexDataMap.entrySet().forEach(stringRealTimeDataEntry -> {
            String deviceId = stringRealTimeDataEntry.getKey().split(KEY_SPLITTER)[0];
            String indexId = stringRealTimeDataEntry.getKey().split(KEY_SPLITTER)[1];
            DeviceDataVo deviceDataVo = baseDataMap.get(deviceId);
            if (Objects.nonNull(deviceDataVo)) {
                deviceDataVo.getRealTimeData().put(indexId, stringRealTimeDataEntry.getValue());
            }
        });
        return baseDataMap;
    }
}
