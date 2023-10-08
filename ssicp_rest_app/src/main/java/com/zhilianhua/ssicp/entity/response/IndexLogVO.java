package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

import java.util.List;

@Data
public class IndexLogVO {
    /**
     * 巡检对象
     */
    private String deviceName;

    /**
     * 巡检指标
     */
    private String index;

    /**
     * 单位
     */
    private String unit;

    /**
     * 指标值
     */
    private String indexValue;

    private List<IndexLogVO> indexLogVOS;
}
