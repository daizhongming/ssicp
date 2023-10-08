package com.zhilianhua.ssicp.entity.request.searchparams;

import com.zhilianhua.ssicp.entity.request.searchparams.common.OrderPageParams;
import lombok.Data;

import java.util.Map;

@Data
public class KnowledgeBaseParams extends OrderPageParams {
    /**
     * 关键字
     */
    private String keyword;


    @Override
    public Map<String, String> setPropsMap() {
        return OrderPageParams.mapBuilder("createTime", "create_time");
    }
}
