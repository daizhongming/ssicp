package com.zhilianhua.ssicp.entity.db;
import lombok.Data;
import java.io.Serializable;

/**
 * @description 知识关联代办
 * @date 2023-08-18
 */
@Data
public class KonwledgeBusiness implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    private String id;

    /**
    * 触发类型（alarm0，alarm1，alarm2，inspection，daycheck，yearcheck）
    */
    private String type;

    /**
    * 事件id
    */
    private String businessId;

    /**
     * 装置名称
     */
    private String deviceName;

    /**
     * 触发指标
     */
    private String indexValue;

    /**
    * 知识id
    */
    private String baseId;

    /**
    * 知识明细id
    */
    private String dataId;

    /**
    * 评价(0.有效 1.无效 2.待评价)
    */
    private String appraise;

}