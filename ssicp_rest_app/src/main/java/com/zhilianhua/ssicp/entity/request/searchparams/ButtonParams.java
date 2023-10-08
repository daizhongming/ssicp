package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;

import java.util.Map;

@Data
public class ButtonParams extends OrderPageParams {
    private String keyword;

    private String type;

    @Override
    public Map<String, String> setPropsMap() {
        return OrderPageParams.mapBuilder("type", "type");
    }
}
