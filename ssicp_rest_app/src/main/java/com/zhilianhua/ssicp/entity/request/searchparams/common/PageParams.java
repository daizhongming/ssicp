package com.zhilianhua.ssicp.entity.request.searchparams.common;

import lombok.Data;

@Data
public class PageParams {
    private int size = 10;
    private int page = 1;

    public int getOffset() {
        return (page - 1) * size;
    }
}
