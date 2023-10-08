package com.zhilianhua.ssicp.entity.response;

import lombok.Data;

@Data
public class MessageAnalyseEntity {
    private String type;
    private Integer unReadCount;
    private Integer readCount;
}
