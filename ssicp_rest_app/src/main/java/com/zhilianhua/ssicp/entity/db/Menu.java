package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

@Data
public class Menu {
    private String title;
    private String code;
    private Integer level;
    private Integer sort;
    private String parentCode;
}
