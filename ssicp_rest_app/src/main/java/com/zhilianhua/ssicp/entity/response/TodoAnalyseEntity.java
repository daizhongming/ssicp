package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class TodoAnalyseEntity {
    private String type;
    private Integer total = 0;
    private Integer handled = 0;
    private Integer unHandle = 0;
}
