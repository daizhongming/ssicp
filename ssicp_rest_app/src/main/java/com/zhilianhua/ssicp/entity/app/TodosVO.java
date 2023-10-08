package com.zhilianhua.ssicp.entity.app;

import lombok.Data;

import java.util.Date;

@Data
public class TodosVO {
    private String type;
    private Object extra;
    private Date time;
//    private Date time;
//    private String businessId;
}
