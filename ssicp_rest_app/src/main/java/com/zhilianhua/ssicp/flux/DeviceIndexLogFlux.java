package com.zhilianhua.ssicp.flux;

import com.influxdb.client.QueryApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.zhilianhua.ssicp.entity.flux.DeviceIndexLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class DeviceIndexLogFlux {
    @Autowired
    private WriteApiBlocking writeApiBlocking;
    @Autowired
    private QueryApi queryApi;
    private static final String FIND_BY_INDEX_ID_FLUX_TEMPLATE = "" +
            "from(bucket:\"ssicp\") " +
            "|> range(start: %d, stop: %d) " +
            "|> filter(fn: (r) => r._measurement == \"deviceIndexLog\")\n" +
            "|> filter(fn: (r) => r.deviceId == \"%s\")" +
            "|> filter(fn: (r) => r.indexId == \"%s\")";

    public List<DeviceIndexLog> findByIndexId(Date start, Date stop, String deviceId, String indexId) {
        String flux = String.format(FIND_BY_INDEX_ID_FLUX_TEMPLATE, start.getTime(), stop.getTime(), deviceId, indexId);
        log.debug("findByIndexId flux:{}", flux);
        return queryApi.query(flux, DeviceIndexLog.class);
    }

    public void add(DeviceIndexLog deviceIndexLog) {
        writeApiBlocking.writeMeasurement(WritePrecision.MS, deviceIndexLog);
    }

    public void addList(List<DeviceIndexLog> deviceIndexLogList) {
        writeApiBlocking.writeMeasurements(WritePrecision.MS, deviceIndexLogList);
    }

    private static final String FIND_LAST_FLUX_TEMPLATE = "" +
            "from(bucket: \"ssicp\")\n" +
            "|> range(start: -1d)\n" +
            "|> filter(fn: (r) => r._measurement == \"deviceIndexLog\")\n" +
            "|> group(columns:[\"deviceId\",\"indexId\"])\n" +
            "|> last()\n" +
            "|> keep(columns: [\"deviceId\", \"indexId\", \"_value\", \"_time\", \"unit\"])\n";

    public List<DeviceIndexLog> findLast() {
        log.debug("findLast flux:{}", FIND_LAST_FLUX_TEMPLATE);
        return queryApi.query(FIND_LAST_FLUX_TEMPLATE, DeviceIndexLog.class);
    }


}
