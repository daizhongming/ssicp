package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class DecisionPlan {
    private Integer id;
    private String name;
    private Date uploadTime;
    private String uploadUser;
    private String fileCode;
    private Integer readCount;
    private Date lastReadTime;
}
