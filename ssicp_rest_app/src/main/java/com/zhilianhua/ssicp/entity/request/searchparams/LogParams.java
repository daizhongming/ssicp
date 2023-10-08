package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Data
public class LogParams extends OrderPageParams {
    private String keyword;
    private Integer successFlag;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRangeEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRangeStart;

    @Override
    public Map<String, String> setPropsMap() {
        return OrderPageParams.mapBuilder("createTime", "create_time", "costTime", "cost_time");
    }
}
