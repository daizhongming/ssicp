package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Data
public class DeviceAlarmLogSearchParams extends OrderPageParams {
    private String keyword;
    private Integer alarmLevel;
    @NotNull(message = "请选择处理状态")
    private Integer handleStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRangeEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRangeStart;

    @Override
    public Map<String, String> setPropsMap() {
        return OrderPageParams.mapBuilder("startTime", "dal2.start_time", "alarmLevel", "dal2.alarm_level");
    }
}