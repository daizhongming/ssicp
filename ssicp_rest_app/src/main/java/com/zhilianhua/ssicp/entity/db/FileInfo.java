package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

@Data
public class FileInfo {
    private String code;
    private String name;
    private Long size;
    private String type;
}
