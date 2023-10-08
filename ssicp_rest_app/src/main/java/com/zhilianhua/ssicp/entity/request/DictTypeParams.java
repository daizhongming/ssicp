package com.zhilianhua.ssicp.entity.request;

import com.zhilianhua.ssicp.entity.request.searchparams.common.PageParams;
import lombok.Data;


@Data
public class DictTypeParams extends PageParams {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 类型code
     */
    private String typecode;


}
