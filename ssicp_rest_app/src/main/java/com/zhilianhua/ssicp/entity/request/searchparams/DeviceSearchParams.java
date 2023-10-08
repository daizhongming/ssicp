package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import lombok.Data;
import java.util.Map;

@Data
public class DeviceSearchParams extends OrderPageParams {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 设备类型
     */
    private String parentDeviceType;
    @Override
    public Map<String, String> setPropsMap() {
        return null;
    }
}
