package com.zhilianhua.ssicp.entity.request.app;

import lombok.Data;

@Data
public class ReadMessageParams {
    private Integer allRead = 0;
    private Long messageId;
    private String userName;
}
