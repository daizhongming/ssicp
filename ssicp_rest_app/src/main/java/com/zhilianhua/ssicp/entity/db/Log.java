package com.zhilianhua.ssicp.entity.db;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private Integer id;
    private Date createTime;
    private String createUserName;
    private String createUserNickName;
    private String classTitle;
    private String methodTitle;
    private String ip;
    private String request;
    private String response;
    private String error;
    private Long costTime;
}
